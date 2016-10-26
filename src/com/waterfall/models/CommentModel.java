package com.waterfall.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="commentmodel")
@NamedQuery(name="CommentModel.findAll", query="SELECT c FROM CommentModel c")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
	@JoinColumn(name = "commentownerid")
	@JsonIgnore
	private UserModel owner;
	
	@Transient
	private Long commentownerid;

	public CommentModel() {
	}

	@XmlTransient
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


	@XmlTransient
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

	public Long getCommentownerid() {
		commentownerid = owner.getUserid();
		return commentownerid;
	}

	public void setCommentownerid(Long commentownerid) {
		this.commentownerid = commentownerid;
	}

	

	

}