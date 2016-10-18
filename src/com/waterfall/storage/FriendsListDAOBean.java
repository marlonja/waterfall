package com.waterfall.storage;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.FriendsListModel;

@Stateful
public class FriendsListDAOBean {

	@PersistenceContext
	private EntityManager em;
	

	public boolean storeFriendsList(FriendsListModel friendsListModel) {
		System.out.println("DAO: store friendslist");
		if (em.merge(friendsListModel) != null) {
			return true;
		} else {
			return false;
		}
		
	}
}
