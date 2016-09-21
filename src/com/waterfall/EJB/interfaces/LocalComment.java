package com.waterfall.EJB.interfaces;

import javax.ejb.Local;

import com.waterfall.models.Comment;

@Local
public interface LocalComment {

	boolean storeComment(Comment comment);
}
