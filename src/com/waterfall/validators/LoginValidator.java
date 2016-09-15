package com.waterfall.validators;

import javax.ejb.Stateless;

import com.waterfall.models.User;

@Stateless
public class LoginValidator {

	public boolean validateUserPassword(User userFromDatabase,User userToCheckInDatabase) {
		if(userToCheckInDatabase.getPassword().equals(userFromDatabase.getPassword())){
			return true;
		}
		return false;
	}
	

}
