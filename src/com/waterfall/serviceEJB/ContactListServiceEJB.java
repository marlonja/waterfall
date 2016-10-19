package com.waterfall.serviceEJB;



import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.models.ContactListModel;

import com.waterfall.storage.ContactListDAOBean;

@Stateless
public class ContactListServiceEJB implements LocalContactList{

	@EJB
	private ContactListDAOBean contactListDAOBean;

	@Override
	public boolean storeContactList(ContactListModel contactListModel) {
		
		return contactListDAOBean.storeContactList(contactListModel);
	}

	@Override
	public ContactListModel getContactListById(Long contactListId) {
		return contactListDAOBean.getContactListById(contactListId);
	}
	
	


}
