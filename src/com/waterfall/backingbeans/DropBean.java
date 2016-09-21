package com.waterfall.backingbeans;

import java.io.Serializable;
import java.sql.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.waterfall.EJB.UserEJB;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.Droplet;
import com.waterfall.models.User;

@Named(value="dropBean")
@SessionScoped
public class DropBean implements Serializable{

	private static final long serialVersionUID = 2772076160829404613L;
	
	private String content;
	private User owner;
	
	@EJB
	LocalUser userEJB;
	
	@EJB
	LocalDrop dropEJB;

	public String createNewDrop() {
		
		User user = userEJB.getUser(26l);
		
		Droplet droplet = new Droplet();
		droplet.setContent("Hello hello");
		droplet.setOwner(user);
		
		dropEJB.storeDrop(droplet);
		
		System.out.println("skapar droppe" + user);
		
		return "hej";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
}
