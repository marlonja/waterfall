package com.waterfall.storage;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.DropModel;

@Stateful
public class DropDAOBean {

	@PersistenceContext
	private EntityManager em;

	public boolean storeDrop(DropModel dropModel) {
		System.out.println("DAO: store drop");

		if (em.merge(dropModel) != null) {
			return true;
		} else {
			return false;
		}
	}

	public DropModel getDropById(Long dropId) {
		return em.find(DropModel.class, dropId);
	}

	public List<DropModel> getAllDrops() {
		return em.createNamedQuery("DropModel.findAll").getResultList();
	}

	public List<DropModel> findDropsByContent(String searchWord) {
		return em.createNamedQuery("DropModel.findDropContentFromSearch")
				.setParameter("content", "%" + searchWord + "%").getResultList();
	}



}
