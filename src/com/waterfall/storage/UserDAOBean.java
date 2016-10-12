package com.waterfall.storage;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;

@Stateful
public class UserDAOBean {

	@PersistenceContext
	private EntityManager em;

	public boolean storeUser(UserModel userModel) {
		if (em.merge(userModel) != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<UserModel> getAll() {
		return em.createNamedQuery("UserModel.findAll").getResultList();
	}

	public UserModel getUserByUsername(String userToCheckInDatabase) {
		try {
			UserModel userModel = (UserModel) em.createNamedQuery("UserModel.findByUsername")
					.setParameter("username", userToCheckInDatabase).getSingleResult();

			return userModel;
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean isEmailInDatabaseUnique(String userEmail) {
		try {
			if (em.createNamedQuery("UserModel.findByEmail").setParameter("email", userEmail)
					.getSingleResult() != null) {
				return false;
			}
		} catch (NoResultException e) {
			return true;
		}
		return true;

	}

	public boolean isUsernameInDatabaseUnique(String username) {
		UserModel userModel = new UserModel();
		userModel.setUsername(username);
		if (getUserByUsername(userModel.getUsername()) == null) {
			return true;
		}
		return false;
	}

	public UserModel getUserById(Long userId) {
		return em.find(UserModel.class, userId);
	}

	public List<UserModel> searchDropsFromUserTable(String searchWord) {
		List<UserModel> userModels = new ArrayList<UserModel>();

		try {
			userModels.addAll(em.createNamedQuery("UserModel.findByUsername")
					.setParameter("username", "%" + searchWord + "%").getResultList());

			userModels.addAll(em.createNamedQuery("UserModel.findByCountry")
					.setParameter("country", "%" + searchWord + "%").getResultList());

			userModels.addAll(em.createNamedQuery("UserModel.findByCity").setParameter("city", "%" + searchWord + "%")
					.getResultList());

			userModels.addAll(em.createNamedQuery("UserModel.findByFirstName").setParameter("firstname", searchWord)
					.getResultList());

			userModels.addAll(em.createNamedQuery("UserModel.findByLastName").setParameter("lastname", searchWord)
					.getResultList());

		} catch (NoResultException e) {
			return null;
		}

		return userModels;

	}

	public List<UserModel> getUsersByGender(String string) {
		return em.createNamedQuery("UserModel.findByGender").setParameter("gender", string).getResultList();

	}

	public List<UserModel> getUsersByAge(int birthYear) {
		return em.createNamedQuery("UserModel.findByAge").setParameter("birthdate", birthYear + "%").getResultList();
		
	}

}
