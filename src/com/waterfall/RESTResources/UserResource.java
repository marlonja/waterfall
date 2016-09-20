package com.waterfall.RESTResources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.User;


@Path("/users")
public class UserResource {
	
	@EJB
	private LocalUser userEJB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return userEJB.getAll();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userId") Long userId) {
		return userEJB.getUser(userId);
	}
}
