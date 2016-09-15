package com.waterfall.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 3773988104720989698L;
	private List<User> userList;
	
	@EJB
	private LocalUser userEJB;
	
	
	public String search(){
		userList = userEJB.getAll();
		
		return "all";
	}
	
	public String checkUser(){
		String temp = "asdd";
		userList = userEJB.getAll();
//		for(int i=0; i<userList.size();i++){
//			
//			if(userList.get(i).getUsername().equals(temp)){
//				System.out.println("User found");
//			}else{
//				System.out.println("No user found");
//			}
//		}
		
		
		return "";
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	
}
