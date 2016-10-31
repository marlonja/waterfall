package com.waterfall.RESTResources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.apache.jasper.tagplugins.jstl.ForEach;

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;

@WebService
public class DropSoapResource {
	
	@EJB
	private LocalDrop dropEjb;
	
	@EJB
	private LocalUser userEjb;
	
	public DropModel createDropModel(Long ownerId, String content) {
		DropModel dropModel = new DropModel();
		
		dropModel.setOwner(userEjb.getUser(ownerId));
		dropModel.setContent(content);
		dropModel.setCreationDate(LocalDateTime.now());
		
		if(dropEjb.storeDrop(dropModel)) {
			return dropModel;
		}else {
			return null;
		}
	}
	
	public DropModel getDropModel(Long dropModelId) {
		return dropEjb.getDrop(dropModelId);
	}
	
	public DropModel updateDropModel(Long dropModelId, String content) {
		DropModel dropModel = dropEjb.getDrop(dropModelId);
		dropModel.setContent(content);
		dropModel.setCreationDate(LocalDateTime.now());
		if(dropEjb.storeDrop(dropModel)) {
			dropModel.setComments(new ArrayList<>());
			return dropModel;
		}else {
			return null;
		}
	}
	
	public DropModel deleteDropModel(Long dropModelId) {
		DropModel dropModel = dropEjb.getDrop(dropModelId);
		dropEjb.deleteDrop(dropModel);
		
		return new DropModel();
	}
	
	public List<DropModel> getAllDrops() {
		List<DropModel> dropModelList = dropEjb.getAllDrops();
		
		for (DropModel dropModel : dropModelList) {
			dropModel.setComments(removeOwnerFromCommentList((Vector<CommentModel>) dropModel.getComments()));
		}
		
		return dropModelList;
	}
	
	private List<CommentModel> removeOwnerFromCommentList(Vector<CommentModel> commentList) {

		for (CommentModel commentModel : commentList) {
			commentModel.setOwner(null);
			commentModel.setDropHost(null);
		}
		return commentList;
	}
	  
}