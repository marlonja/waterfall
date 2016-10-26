package com.waterfall.models;

import java.io.Serializable;
import java.security.acl.Owner;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="dropmodel")
@NamedQueries({ @NamedQuery(name = "DropModel.findAll", query = "SELECT d FROM DropModel d"),
		@NamedQuery(name = "DropModel.findDropContentFromSearch", query = "SELECT d FROM DropModel d WHERE d.content LIKE :content"),
		})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DropModel implements Serializable {

	private static final long serialVersionUID = -2443095827173416242L;

	@Id
	private Long dropid;

	private String content;

	private LocalDateTime creationDate;

	@ManyToOne
	@JoinColumn(name = "dropownerid")
	@JsonIgnore
	private UserModel dropowner;
	
	@Transient
	private Long dropownerid;

	
	@OneToMany(mappedBy = "dropHost",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<CommentModel> comments;

	public DropModel() {
	}

	@XmlTransient
	public UserModel getOwner() {
		return dropowner;
	}

	public void setOwner(UserModel owner) {
		this.dropowner = owner;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@XmlTransient
	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

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


	public Long getDropownerid() {
		dropownerid = dropowner.getUserid();
		return dropownerid;
	}

	public void setDropownerid(Long dropownerid) {
		this.dropownerid = dropownerid;
	}

	
	
	

}