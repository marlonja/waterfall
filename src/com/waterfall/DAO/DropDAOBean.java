package com.waterfall.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.Drop;

@Stateful
public class DropDAOBean {
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean storeDrop(Drop drop) {
		System.out.println("DAO: store drop");
		
		if(em.merge(drop) != null){
			return true;
		}else {
			return false;
		}
	}
}
