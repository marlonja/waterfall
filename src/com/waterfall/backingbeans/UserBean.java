package com.waterfall.backingbeans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;

import sun.reflect.generics.tree.VoidDescriptor;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 3773988104720989698L;
	private String firstName;
	private String lastName;
	private String username;
	private String city;
	private String dob;
	private String email;
	private String gender;
	
	@EJB 
	private LocalUser userEJB;
	
	public String registerNewUser(){
		User user = new User();
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setUsername(username);
		user.setEmail(email);
		user.setCity(city);
		user.setGender(gender);
		
		// temporary date for database purposes
		Date exampleDate = new Date(0,0,0);
		exampleDate.setDate(14);
		exampleDate.setYear(1964);
		exampleDate.setMonth(04);
		
		user.setBirthdate(exampleDate);
		
		userEJB.saveUser(user);
		
//		System.out.println(user);
//		System.out.println("I BEAN!");
		
		
		return "index";
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}
}
