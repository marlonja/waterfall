package com.waterfall.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.User;



@Stateful
public class UserDAOBean {
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean saveUser(User user) {
		
		if(em.merge(user) != null){
			return true;
		}
		return false;
	}

}
