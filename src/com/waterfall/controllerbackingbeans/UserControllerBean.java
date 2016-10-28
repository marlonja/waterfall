package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.ContactListModel;
import com.waterfall.models.UserModel;

@Named(value = "userControllerBean")
@SessionScoped
public class UserControllerBean implements Serializable {

	private static final long serialVersionUID = 3773988104720989698L;
	private String usernameSearch;
	private UserModel loggedInUser;
	private String contactListName;
	private String errorMessage;
	private List<ContactListModel> contactListModels;
	private boolean addContactNotValid;

	@EJB
	private LocalUser userEJB;
	
	@EJB
	private LocalContactList contactListEJB;
	
	@PostConstruct
	public void init() {
		loggedInUser = userEJB.getUserFromSession("loggedInUser");
		contactListModels = loggedInUser.getContactList();
	}
	
	public void updateUser(UserModel user) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		user.setPassword(PBKDF2.generatePasswordHash(user.getVisiblePassword()));
		userEJB.storeUser(user);

	}

	private UserModel searchUserByUsername() {
		UserModel userToSearch = new UserModel();
		userToSearch.setUsername(usernameSearch);
		userToSearch = userEJB.getUserByUsername(usernameSearch);
		return userToSearch;
	}
	
	public List<ContactListModel> getContactLists() {
		init();
		return loggedInUser.getContactList();
		
	}
	
	public String createNewContactlist() {
		ContactListModel contactListModel = new ContactListModel();
		loggedInUser = userEJB.getUserFromSession("loggedInUser");
		
		contactListModel.setContactlistname(contactListName);
		contactListModel.setOwner(loggedInUser);
		loggedInUser.getContactList().add(contactListModel);
		contactListEJB.storeContactList(contactListModel);
		contactListModels = userEJB.getUser(loggedInUser.getUserid()).getContactList();
		return "profile-page";
	}
	
	public String addContactToList(ContactListModel contactListModel){
		errorMessage = userEJB.controlUserContactList(contactListModel, usernameSearch);
		if(errorMessage.equals("ok")){
			errorMessage = "";
			setAddContactNotValid(false);
			
			contactListModel.addContact(searchUserByUsername());
			contactListEJB.storeContactList(contactListModel);
		}else{
			setAddContactNotValid(true);
			//return "profile-page";
		}
		return "profile-page";
	}

	public String getUsernameSearch() {
		return usernameSearch;
	}

	public void setUsernameSearch(String usernameSearch) {
		this.usernameSearch = usernameSearch;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isAddContactNotValid() {
		return addContactNotValid;
	}

	public void setAddContactNotValid(boolean addContactNotValid) {
		this.addContactNotValid = addContactNotValid;
	}

	public List<ContactListModel> getContactListModels() {
		return contactListModels;
	}

	public void setContactListModels(List<ContactListModel> contactListModels) {
		this.contactListModels = contactListModels;
	}

	

}
