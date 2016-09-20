package com.waterfall.DAO;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.waterfall.models.User;




@Stateful
public class UserDAOBean {
	
	@PersistenceContext
	private EntityManager em;

	public boolean storeUser(User user) {
		if(em.merge(user) != null){
			return true;
		}else{
			return false;
		}
	}

	public List<User> getAll() {
		return em.createNamedQuery("User.findAll").getResultList();
	}

	public User getUserByUsername(User userToCheckInDatabase){
		try{
			User user = (User)em.createNamedQuery("User.findByUsername")
			    .setParameter("username", userToCheckInDatabase.getUsername())
			    .getSingleResult();
			
			return user;
		}catch(NoResultException e){
			return null;
		}
	}
	public boolean isEmailInDatabaseUnique(String userEmail){
		try{
			if(em.createNamedQuery("User.findByEmail")
				.setParameter("email", userEmail).getSingleResult() != null){
				return false;
				}
		}catch(NoResultException e){
			return true;
		}
		return true;
		
	}

	public boolean isUsernameInDatabaseUnique(String username) {
		User user = new User();
		user.setUsername(username);
		if(getUserByUsername(user) == null){
			return true;
		}
		return false;
	}


	public User getUserById(Long userId) {
		return em.find(User.class, userId);
	}

}
