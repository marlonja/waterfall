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

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.CommentModel;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;

import jersey.repackaged.com.google.common.collect.Lists;

@Named(value="dropControllerBean")
@SessionScoped
public class DropControllerBean implements Serializable{

	private static final long serialVersionUID = 2772076160829404613L;
	
	private String content;
	private String commentContent;
	private List<DropModel> dropList;
	private String searchWord;
	private List<DropModel> dropListFromSearch;
	private UserModel userFromSearch;
	private LinkedHashSet<DropModel> dropHashSet;
	
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
	
	public String searchDrop(){
		dropListFromSearch = new ArrayList<DropModel>();
		dropHashSet = new LinkedHashSet<DropModel>();
		String[] searchArray = searchWord.split(" ");
		
		for(int i = 0; i < searchArray.length; i++){
			dropListFromSearch.addAll(dropEJB.getDropsFromSearch(searchArray[i]));
			System.out.println(searchArray[i] + i);
			
			userFromSearch = userEJB.getUserByUsername(searchArray[i]);
			if(userFromSearch != null){
				dropListFromSearch.addAll(userFromSearch.getDrops());
			
			}
		}
		
		
		
		dropList = dropListFromSearch;
		return "index";
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
		System.out.println(commentContent);
		System.out.println(userEJB.getUserFromSession("loggedInUser"));
		System.out.println("user id: " + userEJB.getUserFromSession("loggedInUser").getUserid());
		System.out.println("drop id:" + dropId);
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

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public List<DropModel> getDropListFromSearch() {
		return dropListFromSearch;
	}

	public void setDropListFromSearch(List<DropModel> dropListFromSearch) {
		this.dropListFromSearch = dropListFromSearch;
	}
	
	
}
