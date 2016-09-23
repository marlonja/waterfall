package com.waterfall.storage;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.CommentModel;

@Stateful
public class CommentDAOBean {

	@PersistenceContext
	private EntityManager em;

	public boolean storeComment(CommentModel commentModel) {
		System.out.println("DAO: store comment");
		if (em.merge(commentModel) != null) {
			return true;
		} else {
			return false;
		}
	}
}