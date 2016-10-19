package com.waterfall.storage;


import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.ContactListModel;

@Stateful
public class ContactListDAOBean {

	@PersistenceContext
	private EntityManager em;
	

	public boolean storeFriendsList(ContactListModel contactListModel) {
		System.out.println("DAO: store friendslist");
		if (em.merge(contactListModel) != null) {
			return true;
		} else {
			return false;
		}
		
	}
}
