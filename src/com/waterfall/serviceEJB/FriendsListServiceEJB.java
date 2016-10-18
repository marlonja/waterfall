package com.waterfall.serviceEJB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalFriendsList;
import com.waterfall.models.FriendsListModel;
import com.waterfall.models.UserModel;
import com.waterfall.storage.FriendsListDAOBean;

@Stateless
public class FriendsListServiceEJB implements LocalFriendsList{

	@EJB
	private FriendsListDAOBean friendsListDAOBean;

	@Override
	public boolean storeFriendsList(FriendsListModel friendsListModel) {
		
		return friendsListDAOBean.storeFriendsList(friendsListModel);
	}
	
	


}
