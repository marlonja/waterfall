package com.waterfall.serviceEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalContact;
import com.waterfall.models.ContactModel;
import com.waterfall.storage.ContactDAOBean;

@Stateless
public class ContactServiceEJB implements LocalContact{
	
	@EJB
	private ContactDAOBean contactDAOBean;

	@Override
	public boolean storeContact(ContactModel contactModel) {
		
		return contactDAOBean.storeContact(contactModel);
	}

}
