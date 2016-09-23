package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;

@Named(value="commentControllerBean")
@SessionScoped
public class CommentControllerBean implements Serializable {

	private static final long serialVersionUID = 1085497880359743418L;
	
	private String content;
	private DropModel dropHost;
	private String creationDate;
	private UserModel owner;
	
	
	@EJB
	LocalComment commentEJB;
	
	@EJB
	LocalDrop dropEJB;
	
	@EJB
	LocalUser userEJB;
	
	public String createNewComment() {
		DropModel dropModel = dropEJB.getDrop(8l);
		UserModel userModel = userEJB.getUser(42l);
		
		System.out.println(dropModel.getContent());
		
		CommentModel commentModel = new CommentModel();
		
		commentModel.setContent("Jag tycker det suger bajs");
		commentModel.setDropHost(dropModel);
		commentModel.setOwner(userModel);
		commentEJB.storeComment(commentModel);
		return "create-drop";
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

	public LocalComment getCommentEJB() {
		return commentEJB;
	}

	public void setCommentEJB(LocalComment commentEJB) {
		this.commentEJB = commentEJB;
	}

	public DropModel getDropHost() {
		return dropHost;
	}

	public void setDropHost(DropModel dropHost) {
		this.dropHost = dropHost;
	}

	public UserModel getOwner() {
		return owner;
	}

	public void setOwner(UserModel owner) {
		this.owner = owner;
	}
	
	

}
