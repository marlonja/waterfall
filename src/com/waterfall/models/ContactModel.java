package com.waterfall.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({

})
public class ContactModel implements Serializable {

	private static final long serialVersionUID = -6304416512235154119L;

	@Id
	private Long contactId;

	@ManyToOne
	@JoinColumn(name = "contactlistid")
	private ContactListModel contactListModel;

	private Long userId;

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public ContactListModel getContactListModel() {
		return contactListModel;
	}

	public void setContactListModel(ContactListModel contactListModel) {
		this.contactListModel = contactListModel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}