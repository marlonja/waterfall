package com.waterfall.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.DropModel;



@Local
public interface LocalDropSearch {
	List<DropModel> searchDropsByContent(String searchWord);
	
	List<DropModel> searchDropsByUserName(String searchWord);

	List<DropModel> searchDropsByUserCountry(String searchWord);

}
