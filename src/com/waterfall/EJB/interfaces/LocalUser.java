package com.waterfall.EJB.interfaces;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;





@Local
public interface LocalUser {

	boolean storeUser(UserModel userModel);

	List<UserModel> getAll();

	UserModel validateLogin(UserModel userToCheckInDatabase);

	UserModel getUser(Long userId);

	UserModel getUserFromSession(String sessionKey);
	
	UserModel getUserByUsername(String username);
	
	UserModel findByCountry(String searchWord);
	
	void setUserInSession(String sessionKey, UserModel userModel);

	void removeUserFromSession(String sessionKey);
	
	String cryptPassword(String password) throws NoSuchAlgorithmException;

}
