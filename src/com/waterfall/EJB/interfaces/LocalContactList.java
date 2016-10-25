package com.waterfall.EJB.interfaces;



import java.util.List;

import javax.ejb.Local;

import org.jboss.logging.LoggingClass;

import com.waterfall.models.ContactListModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;


@Local
public interface LocalContactList {
	
	boolean storeContactList(ContactListModel contactListModel);
	
	ContactListModel getContactListById(Long contactListId);
	
	List<ContactListModel> getAllContactLists();
	
	
}
