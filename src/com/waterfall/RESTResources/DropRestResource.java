package com.waterfall.RESTResources;

import java.time.LocalDateTime;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;

@Path("/drops")
public class DropRestResource {

	@EJB
	private LocalDrop dropEjb;

	@EJB
	private LocalUser userEjb;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}")
	public DropModel createDropModel(@PathParam("userId") Long userId, DropModel dropModel) {

		UserModel dropOwner = userEjb.getUser(userId);

		dropModel.setOwner(dropOwner);
		dropModel.setCreationDate(LocalDateTime.now());

		if (dropEjb.storeDrop(dropModel)) {
			return dropModel;
		} else {
			return null;
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DropModel> getDrops() {
		List<DropModel> dropList = dropEjb.getAllDrops();

		for (DropModel dropModel : dropList) {
			dropModel.setComments(removeOwnerFromCommentList((Vector<CommentModel>) dropModel.getComments()));
		}

		return dropList;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{dropModelId}")
	public DropModel updateDropModel(@PathParam("dropModelId") Long dropModelId, DropModel dropModel) {
		DropModel dropModelToUpdate = dropEjb.getDrop(dropModelId);
		dropModelToUpdate.setContent(dropModel.getContent());
		
		if(dropEjb.storeDrop(dropModelToUpdate)) {
			return dropModelToUpdate;
		}else {
			return null;
		}
	}
	
	@DELETE
	@Path("/{dropModelId}")
	public Response deleteDropModel(@PathParam("dropModelId") Long dropModelId) {
		DropModel dropModel = dropEjb.getDrop(dropModelId);

		dropEjb.deleteDrop(dropModel);
		return Response.status(Response.Status.OK).build();
		
	}
	
	//TODO fix drop comments
	
	
	private List<CommentModel> removeOwnerFromCommentList(Vector<CommentModel> commentList) {

		for (CommentModel commentModel : commentList) {
			commentModel.setOwner(null);
			commentModel.setDropHost(null);
		}
		return commentList;
	}
}
