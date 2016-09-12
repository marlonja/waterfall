package com.waterfall.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.DAO.UserDAOBean;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;

@Stateless
public class UserEJB implements LocalUser {
	
	@EJB
	private UserDAOBean userDAOBean;

	@Override
	public boolean saveUser(User user) {
		return userDAOBean.saveUser(user);
	}

}
