package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;



import com.waterfall.EJB.interfaces.LocalContact;
import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.EJB.interfaces.LocalUser;

import com.waterfall.models.ContactListModel;
import com.waterfall.models.ContactModel;
import com.waterfall.models.UserModel;



@Named(value = "userControllerBean")
@SessionScoped
public class UserControllerBean implements Serializable {

	private static final long serialVersionUID = 3773988104720989698L;
	private List<UserModel> contactList;
	private String usernameSearch;
	private UserModel userToSearch;
	private UserModel loggedInUser;
	private String contactListName;
	private List<ContactListModel> myContactLists;
	
	
	
	@EJB
	private LocalContact contactEJB;

	@EJB
	private LocalUser userEJB;
	
	@EJB
	private LocalContactList contactListEJB;
	
	public void updateUser(UserModel user) {
		userEJB.storeUser(user);
	}	

	public String searchUserByUsername() {
		UserModel userToCheckInDatabase = new UserModel();
		userToCheckInDatabase.setUsername(usernameSearch);
		userToSearch = userEJB.getUserByUsername(usernameSearch);
		contactList = new ArrayList<UserModel>();
		

		return "profile-page";
	}

	public String createNewContactlist() {
		myContactLists = contactListEJB.getAllContactLists();
		System.out.println(myContactLists);
		
		
		loggedInUser = userEJB.getUserFromSession("loggedInUser");		

		ContactListModel contactListModel = new ContactListModel();	
		
		contactListModel.setContactListName(contactListName);
		contactListModel.setContactListOwner(loggedInUser);


		
		contactListEJB.storeContactList(contactListModel);
		System.out.println(contactListModel.getContactListId());
		
		//addContactToList(contactListModel);
	
		return "profile-page";
	}
	
	public String addContactToList(){
		ContactModel contactModel = new ContactModel();
		//List<ContactModel> listOfContacts = new ArrayList<ContactModel>();
		
		contactModel.setUserId(userToSearch.getUserid());		
		contactModel.setContactListModel(contactListEJB.getContactListById(34L));
		
		
		//listOfContacts.add(contactModel);
		contactEJB.storeContact(contactModel);
		
		//return listOfContacts;
		return "profile-page";
	}

//	private List<UserModel> controlUserFriendList(List<UserModel> friendList) {
//		if (usernameSearch.equals(loggedInUser.getUsername())) {
//			System.out.println("Cannot add yourself");
//		} else {
//			for (UserModel userModel : friendList) {
//				if (userModel.getUsername().equals(usernameSearch)) {
//					System.out.println("This friend is already in list");
//
//					return friendList;
//				}
//			}
//			friendList.add(userToSearch);
//			System.out.println("addded friend");
//
//		}
//
//		return friendList;
//	}



	public List<UserModel> getContactList() {
		return contactList;
	}

	public void setContactList(List<UserModel> contactList) {
		this.contactList = contactList;
	}

	public String getUsernameSearch() {
		return usernameSearch;
	}

	public void setUsernameSearch(String usernameSearch) {
		this.usernameSearch = usernameSearch;
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

	public String getContactListName() {
		return contactListName;
	}

	public void setContactListName(String contactListName) {
		this.contactListName = contactListName;
	}

	public List<ContactListModel> getMyContactLists() {
		return myContactLists;
	}

	public void setMyContactLists(List<ContactListModel> myContactLists) {
		this.myContactLists = myContactLists;
	}

}
