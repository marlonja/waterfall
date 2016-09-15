package com.waterfall.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.DAO.UserDAOBean;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;
import com.waterfall.validators.LoginValidator;
import com.waterfall.validators.RegistrationValidator;

@Stateless
public class UserEJB implements LocalUser {

	@EJB
	private UserDAOBean userDaoBean;

	@EJB
	private LoginValidator loginValidator;

	@EJB
	private RegistrationValidator registrationValidator;

	@Override
	public boolean storeUser(User user) {

		return userDaoBean.storeUser(user);

	}

	@Override
	public List<User> getAll() {

		return userDaoBean.getAll();
	}

	@Override
	public boolean validateLogin(User userToCheckInDatabase) {

		System.out.println("Validate login");

		User userFromDatabase = userDaoBean.getUserByUsername(userToCheckInDatabase);

		if (userFromDatabase != null) {
			System.out.println("User finns");
			if (loginValidator.validateUserPassword(userFromDatabase, userToCheckInDatabase)) {
				System.out.println("User finns och password stämmer");
				return true;
			}
			System.out.println("Password stämde inte");
		} else {
			System.out.println("User fanns ej");
		}

		return false;
	}

}
