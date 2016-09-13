package com.waterfall.DAO;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.xml.wss.impl.policy.verifier.UsernameOrSAMLAlternativeSelector;
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


	public List<User> getUserByUsername(User userToCheckInDatabase) {

		
		List<User> users = em.createNamedQuery("User.findByUsername")
			    .setParameter("username", userToCheckInDatabase.getUsername())
			    .getResultList();
		
		if(users.size() > 0){
			System.out.println("det fanns");
			System.out.println(users);
		}else {
			System.out.println("det fanns inte");
		}
			
		
		return users;
	}

}
