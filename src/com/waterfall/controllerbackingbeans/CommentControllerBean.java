package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;

@Named(value="commentControllerBean")
@RequestScoped
public class CommentControllerBean implements Serializable {

	private static final long serialVersionUID = 1085497880359743418L;
	
	private String content;
	private String creationDate;
	
	@EJB
	private LocalComment commentEJB;
	
	@EJB
	private LocalDrop dropEJB;
	
	@EJB
	private LocalUser userEJB;
	
	public CommentModel createNewComment(Long dropId) {
		CommentModel commentModel = new CommentModel();
		commentModel.setContent(content);
		commentModel.setDropHost(dropEJB.getDrop(dropId));
		commentModel.setOwner(userEJB.getUserFromSession("loggedInUser"));
		commentEJB.storeComment(commentModel);
		content = null;
		return commentModel;
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
