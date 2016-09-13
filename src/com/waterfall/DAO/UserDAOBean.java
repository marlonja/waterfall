package com.waterfall.DAO;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.User;




@Stateful
public class UserDAOBean {
	
	@PersistenceContext
	private EntityManager em;
	

	public boolean storeUser(User c) {
		if(em.merge(c) != null){
			return true;
		}else{
			return false;
		}
		
	}


	public List<User> getAll() {
		return em.createNamedQuery("User.findAll").getResultList();
	}

}
