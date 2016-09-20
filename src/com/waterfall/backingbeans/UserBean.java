package com.waterfall.backingbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 3773988104720989698L;
	private List<User> userList;

	@EJB
	private LocalUser userEJB;

	public void updateUser(User user) {
		userEJB.storeUser(user);
	}

	public String search() {
		userList = userEJB.getAll();

		return "all";
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
