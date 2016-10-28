package com.waterfall.storage;


import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.ContactListModel;

@Stateful
public class ContactListDAOBean {

	@PersistenceContext
	private EntityManager em;
	

	public boolean storeContactList(ContactListModel contactListModel) {
		System.out.println("DAO: store friendslist");
		if (em.merge(contactListModel) != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public ContactListModel getContactListById(Long contactListId) {
		return em.find(ContactListModel.class, contactListId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ContactListModel> getAllContactLists() {
		return em.createNamedQuery("ContactListModel.findAll").getResultList();
	}

}
