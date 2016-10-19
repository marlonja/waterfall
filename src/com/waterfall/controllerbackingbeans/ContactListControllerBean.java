package com.waterfall.controllerbackingbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.models.ContactListModel;
import com.waterfall.serviceEJB.UserServiceEJB;

@Named(value = "contactListControllerBean")
@RequestScoped
public class ContactListControllerBean implements Serializable {

	private static final long serialVersionUID = -1370625496866002066L;
	
	@EJB
	private LocalContactList contactListEJB;
	
	@EJB
	private UserServiceEJB userEJB;
	
	

}
