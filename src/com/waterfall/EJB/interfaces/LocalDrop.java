package com.waterfall.EJB.interfaces;

import javax.ejb.Local;

import com.waterfall.models.Drop;

@Local
public interface LocalDrop {

	boolean storeDrop(Drop drop);
}
