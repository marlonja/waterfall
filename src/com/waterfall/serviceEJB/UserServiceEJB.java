package com.waterfall.serviceEJB;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.flow.builder.ReturnBuilder;

import com.sun.xml.registry.uddi.bindings_v2_2.Contact;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.ContactListModel;
import com.waterfall.models.UserModel;
import com.waterfall.storage.UserDAOBean;
import com.waterfall.validators.LoginValidator;
import com.waterfall.validators.RegistrationValidator;

@Stateless
public class UserServiceEJB implements LocalUser {

	@EJB
	private UserDAOBean userDaoBean;

	@EJB
	private LoginValidator loginValidator;

	@EJB
	private RegistrationValidator registrationValidator;
	
	private ExternalContext externalContext;
	
	private Map<String, Object> currentSession;

	@Override
	public boolean storeUser(UserModel userModel) {
		return userDaoBean.storeUser(userModel);

	}

	@Override
	public List<UserModel> getAllUsers() {

		return userDaoBean.getAll();
	}

	@Override
	public UserModel validateLogin(String username, String typedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {

		UserModel userToCheckInDatabase = getUserByUsername(username);
		if(userToCheckInDatabase == null) {
			displayLoginErrorMessage("search-form", "Wrong input");
			return null;
		}else {
			if(PBKDF2.validatePassword(typedPassword, userToCheckInDatabase.getVisiblePassword())) {
				setUserInSession("loggedInUser", userToCheckInDatabase);
				return userToCheckInDatabase;
			}else {
				// Wrong password
				displayLoginErrorMessage("search-form", "Wrong input");
				return null;
			}
		}
	}
	
	@Override
	public void displayLoginErrorMessage(String field, String message) {
		FacesContext.getCurrentInstance().addMessage(field,
				new FacesMessage(message));
		
	}

	@Override
	public UserModel getUser(Long userId) {

		return userDaoBean.getUserById(userId);
	}

	@Override
	public UserModel getUserFromSession(String sessionKey) {
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		currentSession = externalContext.getSessionMap();

		try {
			return (UserModel)currentSession.get(sessionKey);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void setUserInSession(String sessionKey, UserModel userModel) {
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		currentSession = externalContext.getSessionMap();

		try {
			currentSession.put(sessionKey, userModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeUserFromSession(String sessionKey) {
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		currentSession = externalContext.getSessionMap();
		
		try {
			currentSession.put(sessionKey, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public UserModel getUserByUsername(String username) {
		
		return userDaoBean.getUserByUsername(username);
	}

	@Override
	public void deleteUser(UserModel user) {
		userDaoBean.deleteUser(user);
	}

	@Override
	public String controlUserContactList(ContactListModel contactListModel, String username) {
		UserModel loggedInUser = getUserFromSession("loggedInUser");
		String errorMessage = "";
		if(getUserByUsername(username) == null){
			errorMessage = "User not in database";
			return errorMessage;
		}
		if(username.equals(loggedInUser.getUsername())){
			errorMessage = "Can't add yourself";
			return errorMessage;
		}else{
			for(UserModel userModel : contactListModel.getContacts()){
				if(username.equals(userModel.getUsername())){
					errorMessage = "Contact already in list";
					return errorMessage;
				}
			}
		}
		errorMessage = "ok";
		
		return errorMessage;
	}


}
