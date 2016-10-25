package com.waterfall.RESTResources;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserModel userModel) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		
		if(userModel.getPasswordTest() != null){
		String unsaltedPassword = userModel.getPasswordTest();
		userModel.setPassword(PBKDF2.generatePasswordHash(unsaltedPassword));
		}else {
			System.out.println("Nu var pw null");
		}
		
		if(userEJB.storeUser(userModel)) {
			return Response.status(Response.Status.CREATED).entity(userModel).build();
		}else {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel getUser(@PathParam("userId") Long userId) {
		return userEJB.getUser(userId);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public UserModel updateUser(UserModel userModel) {

		userModel.setPassword(userEJB.getUser(userModel.getUserid()).getPassword());

		if (userEJB.storeUser(userModel)) {
			return userModel;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{userId}")
	public Response deleteUser(@PathParam("userId") Long userId) {
		UserModel user = userEJB.getUser(userId);

		if (user != null) {
			userEJB.deleteUser(user);
			return Response.status(Response.Status.OK).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();

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
