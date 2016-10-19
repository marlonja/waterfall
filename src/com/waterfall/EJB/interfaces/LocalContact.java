package com.waterfall.EJB.interfaces;

import javax.ejb.Local;

import com.waterfall.models.ContactModel;

@Local
public interface LocalContact {
	
	boolean storeContact(ContactModel contactModel);

}
