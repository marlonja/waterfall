package com.waterfall.models;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {

	private static final long serialVersionUID = -2986930687226854493L;

	@Id
	private Long commentid;

	private String content;

	private String creationdate;
	
	@ManyToOne
	@JoinColumn(name = "droplethostid")
	private Droplet dropletHost;

	public Comment() {
	}

	public Long getCommentid() {
		return this.commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}

	public Droplet getDropletHost() {
		return dropletHost;
	}

	public void setDropletHost(Droplet dropletHost) {
		this.dropletHost = dropletHost;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

}