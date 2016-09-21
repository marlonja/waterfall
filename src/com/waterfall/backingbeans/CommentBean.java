package com.waterfall.backingbeans;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalDroplet;
import com.waterfall.models.Comment;
import com.waterfall.models.Droplet;

@Named(value="commentBean")
@SessionScoped
public class CommentBean implements Serializable {

	private static final long serialVersionUID = 1085497880359743418L;
	
	private String content;
	private Droplet dropletHost;
	private String creationDate;
	
	
	@EJB
	LocalComment commentEJB;
	
	@EJB
	LocalDroplet dropletEJB;
	
	public String createNewComment() {
		Droplet droplet = dropletEJB.getDroplet(5l);
		
		System.out.println(droplet.getContent());
		
		Comment comment = new Comment();
		
		comment.setContent("Jag tycker det suger bajs");
		comment.setCreationdate(LocalDateTime.now().toString());
		
		commentEJB.storeComment(comment);
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
