package com.waterfall.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.DAO.CommentDAOBean;
import com.waterfall.EJB.interfaces.LocalComment;
import com.waterfall.models.Comment;

@Stateless
public class CommentEJB implements LocalComment {
	
	@EJB
	private CommentDAOBean commentDaoBean;

	@Override
	public boolean storeComment(Comment comment) {
		System.out.println("EJB: store comment");
		return commentDaoBean.storeComment(comment);
	}

}
