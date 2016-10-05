package com.waterfall.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.DropModel;



@Local
public interface LocalDropSearch {
	List<DropModel> searchDropsFromDropTable(String searchWord);	
	
	List<DropModel> searchDropsFromUserTable(String searchWord);

}
