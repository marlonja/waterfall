package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.UserModel;

@Named(value = "loginControllerBean")
@SessionScoped
public class LoginControllerBean implements Serializable {

	private static final long serialVersionUID = 3227244787787534047L;
	private String username;
	private String password;
	private UserModel loggedInUser;

	@EJB
	private LocalUser userEJB;
	
	public String logOutUser(){
		
		
		userEJB.removeUserFromSession("loggedInUser");
		loggedInUser = null;
		
		return "index";
	}
	
	public String loginUser() {
		UserModel userToCheckInDatabase = new UserModel();
		userToCheckInDatabase.setUsername(username);
		userToCheckInDatabase.setPassword(password);
		loggedInUser = userEJB.validateLogin(userToCheckInDatabase);

		if (loggedInUser != null) {
			userEJB.setUserInSession("loggedInUser", loggedInUser);
			return "index";
		}
		return "index";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserModel getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserModel loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

}