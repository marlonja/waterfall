package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.UserModel;
import com.waterfall.utils.DateService;

@Named(value = "loginControllerBean")
@SessionScoped
public class LoginControllerBean implements Serializable {

	private static final long serialVersionUID = 3227244787787534047L;
	private String username;
	private String password;
	private UserModel loggedInUser;

	@EJB
	private LocalUser userEJB;
	
	@Inject
	FilterControllerBean filterControllerBean;

	public String logOutUser() {

		userEJB.removeUserFromSession("loggedInUser");
		loggedInUser = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
	}

	public String loginUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		if(userEJB.validateLogin(username, password) != null){
			loggedInUser = userEJB.validateLogin(username, password);
			filterControllerBean.setFilterList(loggedInUser.getFilterList());
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
