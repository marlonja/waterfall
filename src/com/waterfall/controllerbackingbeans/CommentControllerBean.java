package com.waterfall.controllerbackingbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;

@Named(value="commentControllerBean")
@SessionScoped
public class CommentControllerBean implements Serializable {

	private static final long serialVersionUID = 1085497880359743418L;
	
	private String content;
	private String creationDate;
	
	@EJB
	LocalComment commentEJB;
	
	@EJB
	LocalDrop dropEJB;
	
	@EJB
	LocalUser userEJB;
	
	public String createNewComment(Long dropId) {
		CommentModel commentModel = new CommentModel();
		System.out.println(content);
		System.out.println(userEJB.getUserFromSession("loggedInUser"));
		System.out.println("user id: " + userEJB.getUserFromSession("loggedInUser").getUserid());
		System.out.println("drop id:" + dropId);
		commentModel.setContent(content);
		commentModel.setDropHost(dropEJB.getDrop(dropId));
		commentModel.setOwner(userEJB.getUserFromSession("loggedInUser"));
		commentEJB.storeComment(commentModel);
		content = null;
		return "index";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
