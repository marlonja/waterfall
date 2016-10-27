package com.waterfall.serviceEJB;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.New;

import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.ContactListModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.storage.ContactListDAOBean;

@Stateless
public class ContactListServiceEJB implements LocalContactList{

	@EJB
	private ContactListDAOBean contactListDAOBean;
	
	@EJB
	private LocalUser userEJB;

	@Override
	public boolean storeContactList(ContactListModel contactListModel) {
		
		return contactListDAOBean.storeContactList(contactListModel);
	}

	@Override
	public ContactListModel getContactListById(Long contactListId) {
		return contactListDAOBean.getContactListById(contactListId);
	}

	@Override
	public List<ContactListModel> getAllContactLists() {
		return contactListDAOBean.getAllContactLists();
	}

	@Override
	public List<DropModel> getContactDrops(ContactListModel contactListModel) {
		List<DropModel> contactDrops = new ArrayList<DropModel>();
		for(UserModel userModel : contactListModel.getContacts()){
			contactDrops.addAll(userModel.getDrops());
		}
		return contactDrops;
	}
	
	


}
