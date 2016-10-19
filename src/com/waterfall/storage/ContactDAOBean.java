package com.waterfall.storage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.xml.ws.developer.Stateful;

import com.waterfall.models.ContactModel;

@Stateless
public class ContactDAOBean {

	@PersistenceContext
	private EntityManager em;
	

	public boolean storeContact(ContactModel contactModel) {
	
		if (em.merge(contactModel) != null) {
			return true;
		} else {
			return false;
		}
		
	}
}