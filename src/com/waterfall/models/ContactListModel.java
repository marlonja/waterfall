package com.waterfall.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@NamedQuery(name="Contactlistmodel.findAll", query="SELECT c FROM ContactListModel c")
public class ContactListModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "user_list", joinColumns = 
	@JoinColumn(name = "id", referencedColumnName = "id"), inverseJoinColumns = 
	@JoinColumn(name = "userid", referencedColumnName = "userid"))
	private List<UserModel> contacts;

	private String contactlistname;

	@ManyToOne
	@JoinColumn(name = "ownerid")
	private UserModel owner;
	
	public ContactListModel() {
	}

	public void addContact(UserModel contact){
		this.contacts.add(contact);
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<UserModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<UserModel> contacts) {
		this.contacts = contacts;
	}

}