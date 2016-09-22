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
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionLoggedInUser = externalContext.getSessionMap();

		try {
			UserModel userModel = (UserModel) sessionLoggedInUser.get(sessionKey);
			return userModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void setUserInSession(String sessionKey, UserModel userModel) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionLoggedInUser = externalContext.getSessionMap();

		try {
			sessionLoggedInUser.put(sessionKey, userModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
