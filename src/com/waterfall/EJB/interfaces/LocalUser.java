package com.waterfall.EJB.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.User;





@Local
public interface LocalUser {

	boolean storeUser(User c);

	List<User> getAll();

	User validateLogin(User userToCheckInDatabase);

}
