package com.waterfall.backingbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.locations.CountryService;
import com.waterfall.models.User;
import com.waterfall.validators.RegistrationValidator;

@Named(value="registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = 5654269423508315837L;
	private String firstName;
	private String lastName;
	private String username;
	private String city;
	private Date birthdate;
	private String email;
	private String gender;
	private String password;
	private String country;
	private List<String> allCountries;
	
	@EJB
	RegistrationValidator registrationValidator;

	@EJB
	CountryService countryService;
	
	@EJB
	private LocalUser userEJB;
	
	@PostConstruct
	public void init() {
		setAllCountries(countryService.getAllCountries());
		
	}
	
	public String registerNewUser(){
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setEmail(email);
		user.setBirthdate(birthdate);
		user.setCity(city);
		user.setGender(gender);
		user.setPassword(password);
		user.setCountry(country);
		
//		// temporary date for database purposes
//		Date exampleDate = new Date(0,0,0);
//		exampleDate.setDate(14);
//		exampleDate.setYear(1964);
//		exampleDate.setMonth(04);
		System.out.println(birthdate.toString());
//		user.setBirthdate(exampleDate);
		if(registrationValidator.validateUserForRegistration(user)){
			userEJB.storeUser(user);
			System.out.println(user);
		}
			
		return "index";
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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
	

	public void setCountry(String country){
		this.country = country;
	}
	
	public String getCountry(){
		return country;
	}

	public List<String> getAllCountries() {
		return allCountries;
	}

	public void setAllCountries(List<String> allCountries) {
		this.allCountries = allCountries;
	}
}
