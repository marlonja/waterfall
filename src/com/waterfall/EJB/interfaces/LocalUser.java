package com.waterfall.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.UserModel;





@Local
public interface LocalUser {

	boolean storeUser(UserModel userModel);

	List<UserModel> getAll();

	UserModel validateLogin(UserModel userToCheckInDatabase);

	UserModel getUser(Long userId);

}
