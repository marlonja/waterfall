package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
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

	public String logOutUser() {

		userEJB.removeUserFromSession("loggedInUser");
		loggedInUser = null;

		return "index";
	}

	public String loginUser() {
//		UserModel userToCheckInDatabase = new UserModel();
//		userToCheckInDatabase.setUsername(username);
//		userToCheckInDatabase.setPassword(password);
//		loggedInUser = userEJB.validateLogin(userToCheckInDatabase);
//
//		if (loggedInUser != null) {
//			userEJB.setUserInSession("loggedInUser", loggedInUser);
//			return "index";
//		} else {
//			FacesContext.getCurrentInstance().addMessage("search-form",
//					new FacesMessage("Username or password is incorrect"));
//			return "index";
//		}
		UserModel userToCheckInDatabase = userEJB.getUserByUsername(username);
		if(userToCheckInDatabase == null){
			FacesContext.getCurrentInstance().addMessage("search-form",
			new FacesMessage("User does not exist"));
			return "index";
		}else {
			try {
				if(PBKDF2.validatePassword(password, userToCheckInDatabase.getPasswordhash())){
					loggedInUser = userToCheckInDatabase;
					userEJB.setUserInSession("loggedInUser", loggedInUser);
					return "index";
				}else {
					FacesContext.getCurrentInstance().addMessage("search-form",
					new FacesMessage("Incorrect password"));
					return "index";
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			}
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
