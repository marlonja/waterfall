package com.waterfall.EJB.interfaces;



import javax.ejb.Local;

import org.jboss.logging.LoggingClass;

import com.waterfall.models.ContactListModel;


@Local
public interface LocalContactList {
	
	boolean storeContactList(ContactListModel contactListModel);
	
	ContactListModel getContactListById(Long contactListId);
	
}
