package com.waterfall.serviceEJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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

}
