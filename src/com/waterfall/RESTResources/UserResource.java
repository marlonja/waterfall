package com.waterfall.RESTResources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.flow.builder.ReturnBuilder;
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
	
	@EJB
	private LocalDrop dropEJB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getUsers() {
		return userEJB.getAll();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel getUser(@PathParam("userId") Long userId) {
		return userEJB.getUser(userId);
	}
	
	@GET
	@Path("/{userId}/drops")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DropModel> getUserDrops(@PathParam("userId") Long userId) {
		
		UserModel userModel = userEJB.getUser(userId);
		
		System.out.println("user:" + userModel);
		
		List<DropModel> list = new ArrayList<>();
		list = userModel.getDrops();
		
		System.out.println("listan: " + list);
		
		return list;
	}
}
