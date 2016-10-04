package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.UserModel;
import com.waterfall.utils.CountryService;
import com.waterfall.utils.DateService;
import com.waterfall.validators.RegistrationValidator;

@Named(value = "registrationControllerBean")
@SessionScoped
public class RegistrationControllerBean implements Serializable {

	private static final long serialVersionUID = 5654269423508315837L;
	private String firstName;
	private String lastName;
	private String username;
	private String city;
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	private String email;
	private String gender;
	private String password;
	private String country;
	private byte[] salt;
	private List<String> allCountries;
	private List<Integer> years;

	@EJB
	RegistrationValidator registrationValidator;

	@EJB
	CountryService countryService;
	
	@EJB
	DateService dateService;

	@EJB
	private LocalUser userEJB;

	@PostConstruct
	public void init() {
		setAllCountries(countryService.getAllCountries());
		setYears(dateService.years());
	}

	public String registerNewUser() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		UserModel userModel = new UserModel();
		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setUsername(username);
		userModel.setEmail(email);
		userModel.setCity(city);
		userModel.setGender(gender);
		userModel.setPassword(PBKDF2.generatePasswordHash(password));
		
		
		userModel.setCountry(country);

		@SuppressWarnings("deprecation")
		Date birthDate = new Date((birthYear - 1900), (birthMonth - 1), birthDay);
		userModel.setBirthdate(birthDate);
		System.out.println(birthDay + " " + birthMonth + " " + birthYear);
		System.out.println(birthDate);
		
		if (registrationValidator.validateUserForRegistration(userModel)) {

			userEJB.storeUser(userModel);
			System.out.println("user saved");
			return "index";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registration failed, try again!"));
			return "reg-new-user";
		}
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

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public List<String> getAllCountries() {
		return allCountries;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public void setAllCountries(List<String> allCountries) {
		this.allCountries = allCountries;
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}
}
