package com.waterfall.EJB.interfaces;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;





@Local
public interface LocalUser {

	boolean storeUser(UserModel userModel);

	List<UserModel> getAll();

	UserModel validateLogin(String username, String typedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException;

	UserModel getUser(Long userId);

	UserModel getUserFromSession(String sessionKey);
	
	UserModel getUserByUsername(String username);	
	
	void setUserInSession(String sessionKey, UserModel userModel);

	void removeUserFromSession(String sessionKey);
	
	void displayLoginErrorMessage(String field, String message);

}
