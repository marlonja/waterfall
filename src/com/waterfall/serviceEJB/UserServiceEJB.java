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

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.UserModel;
import com.waterfall.storage.UserDAOBean;
import com.waterfall.utils.ValidationMessageService;
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
	
	@EJB
	private ValidationMessageService validationMessageService;

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
			validationMessageService.errorMsg("Wrong username");
			return null;
		}else {
			if(PBKDF2.validatePassword(typedPassword, userToCheckInDatabase.getVisiblePassword())) {
				setUserInSession("loggedInUser", userToCheckInDatabase);
				return userToCheckInDatabase;
			}else {
				// Wrong password
				validationMessageService.errorMsg("Wrong password");
				return null;
			}
		}
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
			System.out.println("User put in session");
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


}
