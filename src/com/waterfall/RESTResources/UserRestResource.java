package com.waterfall.RESTResources;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.jasper.tagplugins.jstl.ForEach;
import org.eclipse.persistence.jpa.rs.util.ResourceLocalTransactionWrapper;

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.LinkModel;
import com.waterfall.models.UserModel;
import com.waterfall.serviceEJB.UserServiceEJB;
import com.waterfall.storage.UserDAOBean;
import com.waterfall.utils.LinkBuilder;

@Path("/users")
public class UserRestResource {

	@EJB
	private LocalUser userEJB;

	@EJB
	private LocalDrop dropEJB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getUsers(@Context UriInfo uriInfo) {
		
		return provideLinksForUsers(userEJB.getAllUsers(), uriInfo);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserModel userModel) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		
		if(userModel.getVisiblePassword() != null){
		String unsaltedPassword = userModel.getVisiblePassword();
		userModel.setPassword(PBKDF2.generatePasswordHash(unsaltedPassword));
		}else {
			return Response.status(Response.Status.NO_CONTENT).build();
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
	public UserModel getUser(@PathParam("userId") Long userId, @Context UriInfo uriInfo) {

		UserModel userModel = userEJB.getUser(userId);
		
		userModel.addLink(LinkBuilder.buildSelfLink(UserRestResource.class, uriInfo, userId, "Self"));
		userModel.addLink(LinkBuilder.buildDropLink(UserRestResource.class, uriInfo, userId, "Drops"));
		
		return userModel;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public UserModel updateUser(UserModel userModel) {

		userModel.setPassword(userEJB.getUser(userModel.getUserid()).getVisiblePassword());

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
		List<DropModel> dropList = userModel.getDrops();
	
		for (DropModel dropModel : dropList) {
			dropModel.setComments(removeOwnerFromCommentList( (Vector<CommentModel>) dropModel.getComments()));
		}

		return dropList;
	}
	
	public List<UserModel> provideLinksForUsers(List<UserModel> users, UriInfo uriInfo) {
		for (UserModel userModel : users) {
			userModel.addLink(LinkBuilder.buildSelfLink(UserRestResource.class, uriInfo, userModel.getUserid(), "Self"));
			userModel.addLink(LinkBuilder.buildDropLink(UserRestResource.class, uriInfo, userModel.getUserid(), "Drops"));
		}
		
		return users;
	}
	
	private List<CommentModel> removeOwnerFromCommentList(Vector<CommentModel> commentList) {
		
		for (CommentModel commentModel : commentList) {
			commentModel.setOwner(null);
			commentModel.setDropHost(null);
		}
		return commentList;
	}
}
