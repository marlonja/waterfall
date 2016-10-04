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
import com.waterfall.EJB.interfaces.LocalDropSearch;
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
	private String searchWord;
	private ArrayList<DropModel> dropListFromSearch;
	private ArrayList<UserModel> userListFromSearch;
	private UserModel userFromSearch;
	private UserModel userCountryFromSearch;
	private LinkedHashSet<DropModel> dropHashSet;
	
	@EJB
	LocalDropSearch dropSearchEJB;
	
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

	public String searchDrop() {
		dropListFromSearch = new ArrayList<DropModel>();
		userListFromSearch = new ArrayList<UserModel>();
		dropHashSet = new LinkedHashSet<DropModel>();
		String[] searchArray = searchWord.split(" ");

		for (int i = 0; i < searchArray.length; i++) {
			
			dropListFromSearch.addAll(dropSearchEJB.searchDropsByContent(searchArray[i]));

			dropListFromSearch.addAll(dropSearchEJB.searchDropsByUserCountry(searchArray[i]));
			
			dropListFromSearch.addAll(dropSearchEJB.searchDropsByUserName(searchArray[i]));
			


		}

		dropList = removeDuplicatesFromSearchList(dropListFromSearch);
		return "index";
	}

	private List<DropModel> removeDuplicatesFromSearchList(ArrayList<DropModel> dropListFromSearch) {
		for (int i = 0; i < dropListFromSearch.size(); i++) {
			for (int j = i + 1; j < dropListFromSearch.size(); j++) {
				if (dropListFromSearch.get(i).getDropId().equals(dropListFromSearch.get(j).getDropId())) {
					dropListFromSearch.remove(j);
					j = j - 1;
				}
			}
		}
		return dropListFromSearch;
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

	public void setDropListFromSearch(ArrayList<DropModel> dropListFromSearch) {
		this.dropListFromSearch = dropListFromSearch;
	}

	public UserModel getUserCountryFromSearch() {
		return userCountryFromSearch;
	}

	public void setUserCountryFromSearch(UserModel userCountryFromSearch) {
		this.userCountryFromSearch = userCountryFromSearch;
	}

}
