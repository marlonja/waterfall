package com.waterfall.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NamedQuery(name="CommentModel.findAll", query="SELECT c FROM CommentModel c")
@XmlRootElement
public class CommentModel implements Serializable {

	private static final long serialVersionUID = -2986930687226854493L;

	@Id
	private Long commentid;

	private String content;

	private LocalDateTime creationdate;
	
	@ManyToOne
	@JoinColumn(name = "drophostid")
	@JsonIgnore
	private DropModel dropHost;
	
	@Transient
	private Long drophostid;
	
	@OneToOne
	@JoinColumn(name = "ownerid")
	@JsonIgnore
	private UserModel owner;
	
	@Transient
	private Long ownerid;

	public CommentModel() {
	}

	public UserModel getOwner() {
		return owner;
	}

	public void setOwner(UserModel owner) {
		this.owner = owner;
	}

	public Long getCommentid() {
		return this.commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}


	public DropModel getDropHost() {
		return dropHost;
	}

	public void setDropHost(DropModel dropHost) {
		this.dropHost = dropHost;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}
	
	public String getCreationDateFormatted() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(creationdate.toLocalDate());
	}

	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}

	public Long getDropHostid() {
		drophostid = dropHost.getDropId();
		return drophostid;
	}

	public void setDropHostid(Long dropHostid) {
		this.drophostid = dropHostid;
	}

	public Long getOwnerid() {
		ownerid = owner.getUserid();
		return ownerid;
	}

	public void setOwnerid(Long ownerId) {
		this.ownerid = ownerId;
	}

	

}