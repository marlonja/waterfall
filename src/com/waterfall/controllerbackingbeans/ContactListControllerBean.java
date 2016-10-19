package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalContact;
import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.models.ContactListModel;
import com.waterfall.models.ContactModel;
import com.waterfall.models.UserModel;
import com.waterfall.serviceEJB.UserServiceEJB;

@Named(value = "contactListControllerBean")
@RequestScoped
public class ContactListControllerBean implements Serializable {

	private static final long serialVersionUID = -1370625496866002066L;
	
	private UserModel userToSearch;
	private UserModel loggedInUser;
	private String contactListName;
	
	@EJB
	private LocalContact contactEJB;
	
	@EJB
	private LocalContactList contactListEJB;
	
	@EJB
	private UserServiceEJB userEJB;
	
	public String createNewContactlist() {
		loggedInUser = userEJB.getUserFromSession("loggedInUser");		

		ContactListModel contactListModel = new ContactListModel();	
		
		contactListModel.setContactListName(contactListName);
		contactListModel.setContactListOwner(loggedInUser);	
		
		contactListEJB.storeContactList(contactListModel);
		System.out.println(contactListModel.getContactListId());
		
		addContactToList(contactListModel);
	
		return "profile-page";
	}
	
	private List<ContactModel> addContactToList(ContactListModel contactListModel){
		ContactModel contactModel = new ContactModel();
		List<ContactModel> listOfContacts = new ArrayList<ContactModel>();
		
		contactModel.setUserId(userToSearch.getUserid());
		
		contactModel.setContactListModel(contactListEJB.getContactListById(31L));
		
		listOfContacts.add(contactModel);
		contactEJB.storeContact(contactModel);
		
		return listOfContacts;
		
	}

	public UserModel getUserToSearch() {
		return userToSearch;
	}

	public void setUserToSearch(UserModel userToSearch) {
		this.userToSearch = userToSearch;
	}

	public UserModel getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserModel loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public LocalContact getContactEJB() {
		return contactEJB;
	}

	public void setContactEJB(LocalContact contactEJB) {
		this.contactEJB = contactEJB;
	}

	public LocalContactList getContactListEJB() {
		return contactListEJB;
	}

	public void setContactListEJB(LocalContactList contactListEJB) {
		this.contactListEJB = contactListEJB;
	}

	public UserServiceEJB getUserEJB() {
		return userEJB;
	}

	public void setUserEJB(UserServiceEJB userEJB) {
		this.userEJB = userEJB;
	}

	public String getContactListName() {
		return contactListName;
	}

	public void setContactListName(String contactListName) {
		this.contactListName = contactListName;
	}

	
	

}
