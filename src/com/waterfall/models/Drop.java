package com.waterfall.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the drop database table.
 * 
 */
@Entity
@NamedQuery(name="Drop.findAll", query="SELECT d FROM Drop d")
public class Drop implements Serializable {

	private static final long serialVersionUID = -7384053018675385184L;

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String dropid;

	private String content;

	@ManyToOne
	@JoinColumn(name = "ownerid")
	private User owner;

	public Drop() {
	}

	
	
	public User getOwner() {
		return owner;
	}



	public void setOwner(User owner) {
		this.owner = owner;
	}



	public String getDropid() {
		return this.dropid;
	}

	public void setDropid(String dropid) {
		this.dropid = dropid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}