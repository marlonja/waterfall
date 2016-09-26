package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.DropModel;

import jersey.repackaged.com.google.common.collect.Lists;

@Named(value="dropControllerBean")
@SessionScoped
public class DropControllerBean implements Serializable{

	private static final long serialVersionUID = 2772076160829404613L;
	
	private String content;
	private List<DropModel> dropList;
	
	@EJB
	LocalUser userEJB;
	
	@EJB
	LocalDrop dropEJB;

	public List<DropModel> getAllDrops(){
		return (List<DropModel>) Lists.reverse(dropEJB.getAllDrops());
		
	}

	public String createNewDrop() {
		DropModel dropModel = new DropModel();
		dropModel.setContent(content);
		dropModel.setOwner(userEJB.getUserFromSession("loggedInUser"));
		dropModel.setLocation("Gothenburg");
		
		dropEJB.storeDrop(dropModel);
		
		System.out.println("skapar droppe");
		content = null;
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
	
	
}
