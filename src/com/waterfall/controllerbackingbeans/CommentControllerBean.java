package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;

@Named(value="commentControllerBean")
@SessionScoped
public class CommentControllerBean implements Serializable {

	private static final long serialVersionUID = 1085497880359743418L;
	
	private String content;
	private DropModel dropHost;
	private String creationDate;
	
	
	@EJB
	LocalComment commentEJB;
	
	@EJB
	LocalDrop dropEJB;
	
	public String createNewComment() {
		DropModel dropModel = dropEJB.getDrop(7l);
		
		System.out.println(dropModel.getContent());
		
		CommentModel commentModel = new CommentModel();
		
		commentModel.setContent("Jag tycker det suger bajs");
		commentModel.setDropHost(dropModel);
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
	
	

}
