package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;
import com.waterfall.models.ContactListModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.utils.ValidationMessageService;
import com.waterfall.validators.CreateDropValidator;

import jersey.repackaged.com.google.common.collect.Lists;

@Named(value = "dropControllerBean")
@RequestScoped
public class DropControllerBean implements Serializable {

	private static final long serialVersionUID = 2772076160829404613L;

	private String content;
	private String commentContent;
	private List<DropModel> dropList;
	
	@Inject
	FilterControllerBean filterControllerBean;

	@EJB
	CreateDropValidator createDropValidator;

	@EJB
	LocalUser userEJB;

	@EJB
	LocalDrop dropEJB;
	
	@EJB
	LocalContactList contactListEJB;

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
		

		if(createDropValidator.validateDrop(dropModel)){			

			dropEJB.storeDrop(dropModel);
		}
		
		dropList = Lists.reverse(dropEJB.getAllDrops());
		content = null;
		
		return "index";
	}

	public String createNewComment(Long dropId) {
		CommentModel commentModel = new CommentModel();
		
		commentModel.setContent(commentContent);
		commentModel.setDropHost(dropEJB.getDrop(dropId));
		commentModel.setOwner(userEJB.getUserFromSession("loggedInUser"));
		commentEJB.storeComment(commentModel);
		commentContent = null;
		init();
		return "index";

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
