package com.waterfall.RESTResources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;

@WebService
public class UserSoapResource {

	@EJB
	LocalUser userEjb;
	
	
	public UserModel getUser(Long userId) {
		return userEjb.getUser(userId);
	}
	
	public UserModel getUserTest() {
		UserModel userModel = new UserModel();
		DropModel dropModel = new DropModel();
		ArrayList<DropModel> drops = new ArrayList<DropModel>();
		
//		dropModel.setOwner(userEjb.getUser(47l));
//		drops.add(dropModel);
		
//		userModel.setDrops(drops);
		
		userModel.setFirstName("hejhejehej");
		
		return userModel;
	}
	
	public List<UserModel> getAllUsers() {
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		
		users.addAll(userEjb.getAll());
		
		for (UserModel userModel : users) {
			userModel.setDrops(new ArrayList<>());
			
		}
		
		return users;
	}
}
