package com.waterfall.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.Comment;

@Stateful
public class CommentDAOBean {

	@PersistenceContext
	private EntityManager em;

	public boolean storeComment(Comment comment) {
		System.out.println("DAO: store comment");
		if (em.merge(comment) != null) {
			return true;
		} else {
			return false;
		}
	}
}
