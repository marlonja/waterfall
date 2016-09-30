package com.waterfall.serviceEJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalDrop;
import com.waterfall.models.DropModel;
import com.waterfall.storage.DropDAOBean;

@Stateless
public class DropServiceEJB implements LocalDrop {

	@EJB 
	private DropDAOBean dropDaoBean;

	@Override
	public boolean storeDrop(DropModel dropModel) {
		System.out.println("EJB: store drop");
		return dropDaoBean.storeDrop(dropModel);
	}

	@Override
	public DropModel getDrop(Long dropId) {
		System.out.println("EJB: get drop");
		return dropDaoBean.getDropById(dropId);
	}

	@Override
	public List<DropModel> getAllDrops() {
		
		return dropDaoBean.getAllDrops();
	}

	@Override
	public List<DropModel> findDropContentFromSearch(String searchWord) {
		
		return dropDaoBean.findDropContentFromSearch(searchWord);
	}
	
	
}
