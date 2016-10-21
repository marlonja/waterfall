package com.waterfall.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NamedQuery(name="Contactlistmodel.findAll", query="SELECT c FROM ContactListModel c")
public class ContactListModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Long contactid;

	private String contactlistname;

	@ManyToOne
	@JoinColumn(name = "ownerid")
	private UserModel owner;
	
	public ContactListModel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContactid() {
		return this.contactid;
	}

	public void setContactid(Long contactid) {
		this.contactid = contactid;
	}

	public String getContactlistname() {
		return this.contactlistname;
	}

	public void setContactlistname(String contactlistname) {
		this.contactlistname = contactlistname;
	}

	public UserModel getOwner() {
		return this.owner;
	}

	public void setOwner(UserModel owner) {
		this.owner = owner;
	}

}