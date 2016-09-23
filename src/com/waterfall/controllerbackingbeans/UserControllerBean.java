package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
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
	private List<UserModel> userList;

	@EJB
	private LocalUser userEJB;

	public void updateUser(UserModel user) {
		userEJB.storeUser(user);
	}

	public String search() {
		userList = userEJB.getAll();

		return "all";
	}

	public List<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}

}
