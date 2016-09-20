package com.waterfall.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.DAO.DropDAOBean;
import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.models.Drop;

@Stateless
public class DropEJB implements LocalDrop {

	@EJB 
	private DropDAOBean dropDaoBean;

	@Override
	public boolean storeDrop(Drop drop) {
		System.out.println("EJB: store drop");
		return dropDaoBean.storeDrop(drop);
	}
	
	
}
