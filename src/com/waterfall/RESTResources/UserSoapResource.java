package com.waterfall.RESTResources;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.New;
import javax.jws.WebService;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.serviceEJB.UserServiceEJB;

@WebService
public class UserSoapResource {

	@EJB
	private LocalUser userEjb;
	
	public UserModel createUser(String birthdateFromUser, String city, 
						String country, String email, String firstName,
						String gender, String lastName, String pwFromUser,
						String username) {
		UserModel userModel = new UserModel();
		Date birthdate = new Date(0, 0, 0);
		
		//TODO FINISH THIS
		
		userModel.setBirthdate(birthdate);
		userModel.setCity(city);
		userModel.setCountry(country);
		userModel.setDrops(new ArrayList<>());
		userModel.setEmail(email);
		userModel.setFirstName(firstName);
		userModel.setGender(gender);
		userModel.setLastName(lastName);
		userModel.setPassword(pwFromUser);
		userModel.setUsername(username);
		
		return userModel;
	}
	
	public UserModel getUser(Long userId) {
		return userEjb.getUser(userId);
	}
	
	public List<UserModel> getAllUsers() {
		return userEjb.getAll();
	}
	
	
}
