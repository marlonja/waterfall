package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.EJB.interfaces.LocalUser;

import com.waterfall.models.ContactListModel;
import com.waterfall.models.UserModel;


@Named(value = "userControllerBean")
@SessionScoped
public class UserControllerBean implements Serializable {

	private static final long serialVersionUID = 3773988104720989698L;
	private String usernameSearch;
	private UserModel userToSearch;
	private UserModel loggedInUser;
	private String contactListName;

	@EJB
	private LocalUser userEJB;
	
	@EJB
	private LocalContactList contactListEJB;
	
	@PostConstruct
	public void init() {
		loggedInUser = userEJB.getUserFromSession("loggedInUser");
	}
	
	public void updateUser(UserModel user) {
		userEJB.storeUser(user);
	}	

	public String searchUserByUsername() {
		UserModel userToCheckInDatabase = new UserModel();
		userToCheckInDatabase.setUsername(usernameSearch);
		userToSearch = userEJB.getUserByUsername(usernameSearch);
		return "profile-page";
	}
	
	public List<ContactListModel> getContactLists() {
		return loggedInUser.getContactList();
	}

	public String createNewContactlist() {
		ContactListModel contactListModel = new ContactListModel();
		loggedInUser = userEJB.getUserFromSession("loggedInUser");
		
		contactListModel.setContactlistname(contactListName);
		contactListModel.setOwner(loggedInUser);
		
		contactListEJB.storeContactList(contactListModel);
		loggedInUser.getContactList().add(contactListModel);
	
		return "profile-page";
	}
	
	public String addContactToList(ContactListModel contactListModel){
//		List<UserModel> contacts = new ArrayList<UserModel>();
//		contacts.add(userToSearch);
//		contactListModel.setContacts(contacts);
		contactListModel.setContactid(userToSearch.getUserid());
		contactListEJB.storeContactList(contactListModel);
		
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

	
	

}
