package com.waterfall.backingbeans;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3227244787787534047L;
	private String username;
	private String password;
	private User loggedInUser;

	@EJB
	private LocalUser userEJB;
	
	public String logOutUser(){
		
		try{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			if(externalContext != null ){
				externalContext.getSessionMap().clear();
				loggedInUser = null;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	public String loginUser() {
		User userToCheckInDatabase = new User();
		userToCheckInDatabase.setUsername(username);
		userToCheckInDatabase.setPassword(password);
		loggedInUser = userEJB.validateLogin(userToCheckInDatabase);

		
		if (loggedInUser != null) {
			
			System.out.println("Allt var bra, vi kommer lägga user i session");
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionLoggedInUser = externalContext.getSessionMap();
			
			try {
				sessionLoggedInUser.put("loggedInUser", loggedInUser);
			} catch (Exception e) {
				System.out.println("Fel!!!!");
				e.printStackTrace();
			}
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

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

}
