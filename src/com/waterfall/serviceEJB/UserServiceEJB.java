package com.waterfall.serviceEJB;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.waterfall.EJB.interfaces.LocalUser;
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
		System.out.println("Inne i store user");
		return userDaoBean.storeUser(userModel);

	}

	@Override
	public List<UserModel> getAll() {

		return userDaoBean.getAll();
	}

	@Override
	public UserModel validateLogin(UserModel userToCheckInDatabase) {

		UserModel userFromDatabase = userDaoBean.getUserByUsername(userToCheckInDatabase);

		if (userFromDatabase != null) {
			if (loginValidator.validateUserPassword(userFromDatabase, userToCheckInDatabase)) {
				System.out.println("User finns och password st�mmer");
				return userFromDatabase;
			}
		}
		return null;
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
	public UserModel getUserByUsername(UserModel userByUsername) {
		
		return userDaoBean.getUserByUsername(userByUsername);
	}

}
