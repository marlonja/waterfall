package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;

import jersey.repackaged.com.google.common.collect.Lists;

@Named(value = "dropControllerBean")
@SessionScoped
public class DropControllerBean implements Serializable {

	private static final long serialVersionUID = 2772076160829404613L;

	private String content;
	private String commentContent;
	private List<DropModel> dropList;

	@EJB
	LocalUser userEJB;

	@EJB
	LocalDrop dropEJB;

	@EJB
	LocalComment commentEJB;

	@PostConstruct
	public void init() {
		dropList = Lists.reverse(dropEJB.getAllDrops());

	}


	public String createNewDrop() {
		DropModel dropModel = new DropModel();
		dropModel.setContent(content);
		dropModel.setOwner(userEJB.getUserFromSession("loggedInUser"));
		dropModel.setLocation("Gothenburg");

		dropEJB.storeDrop(dropModel);
		dropList = Lists.reverse(dropEJB.getAllDrops());
		System.out.println("skapar droppe");

		content = null;
		return "index";
	}

	public String createNewComment(Long dropId) {
		CommentModel commentModel = new CommentModel();
		
		commentModel.setContent(commentContent);
		commentModel.setDropHost(dropEJB.getDrop(dropId));
		commentModel.setOwner(userEJB.getUserFromSession("loggedInUser"));
		commentEJB.storeComment(commentModel);
		dropList = Lists.reverse(dropEJB.getAllDrops());
		commentContent = null;
		return "/index.xhtml?faces-redirect=true";

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<DropModel> getDropList() {
		return dropList;
	}

	public void setDropList(List<DropModel> dropList) {
		this.dropList = dropList;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}



}
