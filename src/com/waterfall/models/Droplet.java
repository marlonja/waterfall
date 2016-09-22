package com.waterfall.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;


@Entity
@NamedQuery(name = "Droplet.findAll", query = "SELECT d FROM Droplet d")
public class Droplet implements Serializable {

	private static final long serialVersionUID = -2443095827173416242L;

	@Id
	private Long dropletid;

	private String content;

	private LocalDateTime creationDate;

	private String location;

	@ManyToOne
	@JoinColumn(name = "ownerid")
	private User owner;
	
	@OneToMany(mappedBy = "dropletHost")
	private List<Comment> comments;
	

	public Droplet() {
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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

	

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}