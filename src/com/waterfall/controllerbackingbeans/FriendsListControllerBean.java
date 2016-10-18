package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalFriendsList;
import com.waterfall.models.FriendsListModel;
import com.waterfall.serviceEJB.UserServiceEJB;

@Named(value = "friendsListControllerBean")
@RequestScoped
public class FriendsListControllerBean implements Serializable {

	private static final long serialVersionUID = -1370625496866002066L;
	
	@EJB
	private LocalFriendsList friendsListEJB;
	
	@EJB
	private UserServiceEJB userEJB;
	
	

}
