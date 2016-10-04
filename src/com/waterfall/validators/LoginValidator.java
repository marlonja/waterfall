package com.waterfall.validators;

import javax.ejb.Stateless;

import com.waterfall.models.UserModel;

@Stateless
public class LoginValidator {

	public boolean validateUserPassword(UserModel userFromDatabase,UserModel userToCheckInDatabase) {
		if(userToCheckInDatabase.getPassword().equals(userFromDatabase.getPassword())){
			return true;
		}
		return true;
	}
	

}
