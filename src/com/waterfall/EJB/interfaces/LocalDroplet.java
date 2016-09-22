package com.waterfall.EJB.interfaces;

import javax.ejb.Local;

import com.waterfall.models.Droplet;

@Local
public interface LocalDroplet {

	boolean storeDrop(Droplet droplet);
	
	Droplet getDroplet(Long dropletId);
}
