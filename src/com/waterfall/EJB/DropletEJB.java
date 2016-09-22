package com.waterfall.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.DAO.DropletDAOBean;
import com.waterfall.EJB.interfaces.LocalDroplet;
import com.waterfall.models.Droplet;

@Stateless
public class DropletEJB implements LocalDroplet {

	@EJB 
	private DropletDAOBean dropDaoBean;

	@Override
	public boolean storeDrop(Droplet droplet) {
		System.out.println("EJB: store drop");
		return dropDaoBean.storeDrop(droplet);
	}

	@Override
	public Droplet getDroplet(Long dropletId) {
		System.out.println("EJB: get drop");
		return dropDaoBean.getDropById(dropletId);
	}
	
	
}
