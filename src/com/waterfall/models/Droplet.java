package com.waterfall.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the droplet database table.
 * 
 */
@Entity
@NamedQuery(name="Droplet.findAll", query="SELECT d FROM Droplet d")
public class Droplet implements Serializable {

	private static final long serialVersionUID = -2443095827173416242L;

	@Id
	private Long dropletid;

	private String content;
	
	@ManyToOne
	@JoinColumn(name = "ownerid")
	private User owner;

	public Droplet() {
	}
	
	

	public User getOwner() {
		return owner;
	}



	public void setOwner(User owner) {
		this.owner = owner;
	}



	public Long getDropletid() {
		return this.dropletid;
	}

	public void setDropletid(Long dropletid) {
		this.dropletid = dropletid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}