package com.waterfall.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.DAO.UserDAOBean;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;


@Stateless
public class UserEJB implements LocalUser {
	
	@EJB
	private UserDAOBean userDaoBean;

	@Override
	public boolean storeUser(User c) {
		
		return userDaoBean.storeUser(c);
	}

	@Override
	public List<User> getAll() {
		
		return userDaoBean.getAll();
	}

	@Override
	public User validateLogin(User userToCheckInDatabase) {
		
		System.out.println("Kom in i EJB");
		
		userDaoBean.getUserByUsername(userToCheckInDatabase);
		
		return null;
	}
	
	

}
