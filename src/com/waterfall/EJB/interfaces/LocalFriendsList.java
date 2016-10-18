package com.waterfall.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.FriendsListModel;
import com.waterfall.models.UserModel;

@Local
public interface LocalFriendsList {
	
	boolean storeFriendsList(FriendsListModel friendsListModel);
	
}
