package com.waterfall.backingbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.models.User;

@Named(value="loginBean")
@SessionScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 6888006431986226900L;
	private String userName;
	private String password;
	
	public String loginUser(){
		System.out.println(userName + " " + password);
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		return "";
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
