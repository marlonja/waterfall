package com.waterfall.EJB.interfaces;



import javax.ejb.Local;

import com.waterfall.models.ContactListModel;


@Local
public interface LocalContactList {
	
	boolean storeContactList(ContactListModel contactListModel);
	
}
