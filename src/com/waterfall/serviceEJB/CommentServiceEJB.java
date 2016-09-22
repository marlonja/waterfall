package com.waterfall.serviceEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.models.CommentModel;
import com.waterfall.storage.CommentDAOBean;

@Stateless
public class CommentServiceEJB implements LocalComment {
	
	@EJB
	private CommentDAOBean commentDaoBean;

	@Override
	public boolean storeComment(CommentModel commentModel) {
		System.out.println("EJB: store comment");
		return commentDaoBean.storeComment(commentModel);
	}

}
