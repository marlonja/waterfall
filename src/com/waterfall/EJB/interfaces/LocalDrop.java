package com.waterfall.EJB.interfaces;

import javax.ejb.Local;

import com.waterfall.models.DropModel;

@Local
public interface LocalDrop {

	boolean storeDrop(DropModel dropModel);
	
	DropModel getDrop(Long dropId);
}
