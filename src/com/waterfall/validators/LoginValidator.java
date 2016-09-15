package com.waterfall.validators;

import javax.ejb.Stateless;

import com.waterfall.models.User;

@Stateless
public class LoginValidator {

	public boolean validateUserPassword(User userFromDatabase,User userToCheckInDatabase) {
		System.out.println("i validatepass");
		if(userToCheckInDatabase.getPassword().equals(userFromDatabase.getPassword())){
			System.out.println("validate password sant");
			return true;
		}
		System.out.println("validate password falskt");
		return false;
	}
	

}
