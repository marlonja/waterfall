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
	@NamedQuery(name = "FriendsListModel.findAll", query = "SELECT f FROM FriendsListModel f")
})
public class FriendsListModel implements Serializable{

	private static final long serialVersionUID = -6426647291204634601L;
	
	@Id
	private long friendsListId;
	
	@ManyToOne
	@JoinColumn(name = "ownerid")
	private UserModel friendsListOwner;
	
	private String listName;
	
	@OneToMany(mappedBy = "friendsListModel")
	private List<FriendsModel> friends;

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public long getFriendsListId() {
		return friendsListId;
	}

	public void setFriendsListId(long friendsListId) {
		this.friendsListId = friendsListId;
	}

	public List<FriendsModel> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendsModel> friends) {
		this.friends = friends;
	}

	public UserModel getFriendsListOwner() {
		return friendsListOwner;
	}

	public void setFriendsListOwner(UserModel friendsListOwner) {
		this.friendsListOwner = friendsListOwner;
	}
	
	
	
	
}
