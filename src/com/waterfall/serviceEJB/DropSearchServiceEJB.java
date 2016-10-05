package com.waterfall.serviceEJB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalDropSearch;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.storage.DropDAOBean;
import com.waterfall.storage.UserDAOBean;

@Stateless
public class DropSearchServiceEJB implements LocalDropSearch{
	
	@EJB
	private UserDAOBean userDAOBean;
	
	@EJB
	private DropDAOBean dropDAOBean;

	@Override
	public List<DropModel> searchDropsFromDropTable(String searchWord) {
		
		return dropDAOBean.findDropsByContent(searchWord);
	}

	


	@Override
	public List<DropModel> searchDropsFromUserTable(String searchWord) {
		List<DropModel> dropModelList = new ArrayList<DropModel>();
		for(UserModel userModel : userDAOBean.searchDropsByInput(searchWord)){
			dropModelList.addAll(userModel.getDrops());
		}
		return dropModelList;
	}
	
	

}
