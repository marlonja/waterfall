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
public class FriendsModel implements Serializable{

	private static final long serialVersionUID = -6304416512235154119L;
	
	@Id
	private Long friendsId;
	
	@ManyToOne
	@JoinColumn(name = "friendslistmodelid")
	private FriendsListModel friendsListModel;
	
	private Long userId;

	public Long getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(Long friendsId) {
		this.friendsId = friendsId;
	}

	public FriendsListModel getFriendsListModel() {
		return friendsListModel;
	}

	public void setFriendsListModel(FriendsListModel friendsListModel) {
		this.friendsListModel = friendsListModel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
