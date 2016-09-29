package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.UserModel;

@Named(value = "userControllerBean")
@SessionScoped
public class UserControllerBean implements Serializable {

	private static final long serialVersionUID = 3773988104720989698L;
	private List<UserModel> friendList;
	private String usernameSearch;
	private UserModel userToSearch;
	private UserModel loggedInUser;

	@EJB
	private LocalUser userEJB;

	public void updateUser(UserModel user) {
		userEJB.storeUser(user);
	}

	public String search() {
		friendList = userEJB.getAll();

		return "all";
	}

	public String searchUserByUsername() {
		UserModel userToCheckInDatabase = new UserModel();
		userToCheckInDatabase.setUsername(usernameSearch);
		userToSearch = userEJB.getUserByUsername(usernameSearch);
		friendList = new ArrayList<UserModel>();
		friendList.add(userEJB.getUser(46l));
		friendList.add(userEJB.getUser(45l));

		return "profile-page";
	}

	public String addUserToFriendlist() {
		loggedInUser = userEJB.getUserFromSession("loggedInUser");
		friendList = controlUserFriendList(friendList);

		return "profile-page";
	}

	private List<UserModel> controlUserFriendList(List<UserModel> friendList) {
		if (usernameSearch.equals(loggedInUser.getUsername())) {
			System.out.println("Cannot add yourself");
		} else {
			for (UserModel userModel : friendList) {
				if (userModel.getUsername().equals(usernameSearch)) {
					System.out.println("This friend is already in list");

					return friendList;
				}
			}
			friendList.add(userToSearch);
			System.out.println("addded friend");

		}

		return friendList;
	}

	public List<UserModel> getUserList() {
		return friendList;
	}

	public void setUserList(List<UserModel> userList) {
		this.friendList = userList;
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

}
