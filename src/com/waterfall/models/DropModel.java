package com.waterfall.models;

import java.io.Serializable;
import java.security.acl.Owner;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({ @NamedQuery(name = "DropModel.findAll", query = "SELECT d FROM DropModel d"),
		@NamedQuery(name = "DropModel.findDropContentFromSearch", query = "SELECT d FROM DropModel d WHERE d.content LIKE :content"),
		})
@XmlRootElement
public class DropModel implements Serializable {

	private static final long serialVersionUID = -2443095827173416242L;

	@Id
	private Long dropid;

	private String content;

	private LocalDateTime creationDate;

	private String location;

	@ManyToOne
	@JoinColumn(name = "ownerid")
	@JsonIgnore
	private UserModel owner;
	
	@Transient
	private Long ownerid;

//	@OneToMany(mappedBy = "dropHost", fetch=FetchType.EAGER)
//	private Set<CommentModel> comments;

	public DropModel() {
	}

	public UserModel getOwner() {
		return owner;
	}

	public void setOwner(UserModel owner) {
		this.owner = owner;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

//	public Set<CommentModel> getComments() {
//		return comments;
//	}
//
//	public void setComments(Set<CommentModel> comments) {
//		this.comments = comments;
//	}

	public Long getDropId() {
		return this.dropid;
	}

	public void setDropId(Long dropid) {
		this.dropid = dropid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreationDateFormatted() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(creationDate.toLocalDate());
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getOwnerId() {
		ownerid = owner.getUserid();
		return ownerid;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerid = ownerId;
	}
	
	

}