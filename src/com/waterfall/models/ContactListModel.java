package com.waterfall.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
	@NamedQuery(name = "ContactListModel.findAll", query = "SELECT c FROM ContactListModel c")
})
public class ContactListModel implements Serializable{

	private static final long serialVersionUID = -6426647291204634601L;
	
	@Id
	private Long contactListId;
	
	@ManyToOne
	@JoinColumn(name = "ownerid")
	private UserModel contactListOwner;
	
	private String contactListName;
	
	@OneToMany(mappedBy = "contactListModel")
	private List<ContactModel> contacts;

	public Long getContactListId() {
		return contactListId;
	}

	public void setContactListId(Long contactListId) {
		this.contactListId = contactListId;
	}

	public UserModel getContactListOwner() {
		return contactListOwner;
	}

	public void setContactListOwner(UserModel contactListOwner) {
		this.contactListOwner = contactListOwner;
	}

	public String getContactListName() {
		return contactListName;
	}

	public void setContactListName(String contactListName) {
		this.contactListName = contactListName;
	}

	public List<ContactModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactModel> contacts) {
		this.contacts = contacts;
	}

}
	