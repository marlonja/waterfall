package com.waterfall.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.Droplet;

@Stateful
public class DropDAOBean {
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean storeDrop(Droplet droplet) {
		System.out.println("DAO: store drop");
		
		if(em.merge(droplet) != null){
			return true;
		}else {
			return false;
		}
	}
}
