package com.waterfall.EJB.interfaces;

import javax.ejb.Local;

import com.waterfall.models.User;



@Local
public interface LocalUser {

	boolean saveUser(User user);
}
