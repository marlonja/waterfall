package com.waterfall.EJB.interfaces;

import javax.ejb.Local;

import com.waterfall.models.Droplet;

@Local
public interface LocalDrop {

	boolean storeDrop(Droplet droplet);
}
