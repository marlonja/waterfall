package com.waterfall.RESTResources;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
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

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.hashing.pbkdf2.PBKDF2;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.utils.LinkBuilder;
import com.waterfall.validators.RegistrationValidator;

@Path("/users")
public class UserRestResource {

	@EJB
	private LocalUser userEJB;

	@EJB
	private LocalDrop dropEJB;

	@EJB
	private RegistrationValidator registrationValidator;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getUsers(@Context UriInfo uriInfo) {

		return provideLinksForUsers(userEJB.getAllUsers(), uriInfo);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserModel userModel)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

		ArrayList<String> errorMessages = getErrorMessages(userModel);

		if (errorMessages.size() > 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		String unsaltedPassword = userModel.getVisiblePassword();
		userModel.setPassword(PBKDF2.generatePasswordHash(unsaltedPassword));

		if (userEJB.storeUser(userModel)) {
			return Response.status(Response.Status.CREATED).entity(userModel).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("userId") Long userId, @Context UriInfo uriInfo) {

		UserModel userModel = userEJB.getUser(userId);

		if (userModel == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			userModel.addLink(LinkBuilder.buildSelfLink(UserRestResource.class, uriInfo, userId, "Self"));
			userModel.addLink(LinkBuilder.buildDropLink(UserRestResource.class, uriInfo, userId, "Drops"));
			return Response.status(Response.Status.OK).entity(userModel).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(UserModel userModel) {

		ArrayList<String> errorMessages = getErrorMessages(userModel);

		if (errorMessages.size() > 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		userModel.setPassword(userModel.getVisiblePassword());
		userEJB.storeUser(userModel);

		return Response.status(Response.Status.OK).entity(userModel).build();
	}

	@DELETE
	@Path("/{userId}")
	public Response deleteUser(@PathParam("userId") Long userId) {
		UserModel userToDelete = userEJB.getUser(userId);

		if (userToDelete != null) {
			userEJB.deleteUser(userToDelete);
			return Response.status(Response.Status.OK).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();

	}

	@GET
	@Path("/{userId}/drops")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DropModel> getUserDrops(@PathParam("userId") Long userId, @Context UriInfo uriInfo) {
		List<DropModel> dropList = userEJB.getUser(userId).getDrops();

		for (DropModel dropModel : dropList) {
			dropModel.setComments(removeOwnerFromCommentList((Vector<CommentModel>) dropModel.getComments()));
			dropModel
					.addLink(LinkBuilder.buildSelfLink(DropRestResource.class, uriInfo, dropModel.getDropId(), "Self"));
			dropModel.addLink(
					LinkBuilder.buildCommentLink(DropRestResource.class, uriInfo, dropModel.getDropId(), "Comments"));
		}

		return dropList;
	}

	public List<UserModel> provideLinksForUsers(List<UserModel> users, UriInfo uriInfo) {
		for (UserModel userModel : users) {
			userModel
					.addLink(LinkBuilder.buildSelfLink(UserRestResource.class, uriInfo, userModel.getUserid(), "Self"));
			userModel.addLink(
					LinkBuilder.buildDropLink(UserRestResource.class, uriInfo, userModel.getUserid(), "Drops"));
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

	@SuppressWarnings("deprecation")
	private ArrayList<String> getErrorMessages(UserModel userModel) {
		ArrayList<String> errorMessages = registrationValidator.validateUserForRegistration(
				userModel.getBirthdate().getYear(), userModel.getBirthdate().getMonth(),
				userModel.getBirthdate().getDate(), userModel, new ArrayList<>());

		return errorMessages;
	}
}
