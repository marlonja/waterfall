package com.waterfall.RESTResources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.serviceEJB.UserServiceEJB;
import com.waterfall.storage.UserDAOBean;


@Path("/users")
public class UserResource {
	
	@EJB
	private LocalUser userEJB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getUsers() {
		
		System.out.println("innan smäller inte!");
		
		List<UserModel> users = userEJB.getAll();
		
//		for (UserModel userModel : users) {
//			
//			System.out.println(userModel.getDrops());
//			
//			for (DropModel dropModel : userModel.getDrops()) {
//				System.out.println("comments----------");
//				System.out.println(dropModel.getComments());
//			}
//		}
		
		System.out.println("efter smäller inte!");
		
		
		return userEJB.getAll();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel getUser(@PathParam("userId") Long userId) {
		return userEJB.getUser(userId);
	}
	
//	private List<DropModel> getUserDrops(Long userId, List<DropModel> list) {
//		
//		List<DropModel> finalDropList = new ArrayList<DropModel>();
//		
//		for (DropModel dropModel : list) {
//			if(dropModel.getOwnerId() == userId) {
//				System.out.println("Nu var det samma id");
//				finalDropList.add(dropModel);
//			}
//		}
//		
//		return finalDropList;
//	}
}
