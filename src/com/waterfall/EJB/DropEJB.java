package com.waterfall.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.DAO.DropDAOBean;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.models.Droplet;

@Stateless
public class DropEJB implements LocalDrop {

	@EJB 
	private DropDAOBean dropDaoBean;

	@Override
	public boolean storeDrop(Droplet droplet) {
		System.out.println("EJB: store drop");
		return dropDaoBean.storeDrop(droplet);
	}
	
	
}
