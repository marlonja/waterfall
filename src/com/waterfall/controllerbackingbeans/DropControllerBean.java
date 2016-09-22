package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.utils.LocalDateTimeConverter;

@Named(value="dropControllerBean")
@SessionScoped
public class DropControllerBean implements Serializable{

	private static final long serialVersionUID = 2772076160829404613L;
	
	private String content;
	private UserModel owner;
	
	@EJB
	LocalUser userEJB;
	
	@EJB
	LocalDrop dropEJB;
	
	

	public String createNewDrop() {
		
		owner = userEJB.getUserFromSession("loggedInUser");
		
		DropModel dropModel = new DropModel();
		dropModel.setContent(content);
		dropModel.setOwner(owner);
		dropModel.setLocation("Gothenburg");
		
		dropEJB.storeDrop(dropModel);
		
		System.out.println("skapar droppe" + owner);
		
		return "index";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserModel getOwner() {
		return owner;
	}

	public void setOwner(UserModel owner) {
		this.owner = owner;
	}
	
	
}
