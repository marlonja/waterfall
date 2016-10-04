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
	public List<DropModel> searchDropsByContent(String searchWord) {
		
		return dropDAOBean.findDropsByContent(searchWord);
	}

	@Override
	public List<DropModel> searchDropsByUserName(String searchWord) {
		List<DropModel> dropModelList = new ArrayList<DropModel>();
		for(UserModel userModel : userDAOBean.findUsersByUserName(searchWord)){
			dropModelList.addAll(userModel.getDrops());
		}
		return dropModelList;
		
	}

	@Override
	public List<DropModel> searchDropsByUserCountry(String searchWord) {
		List<DropModel> dropModelList = new ArrayList<DropModel>();
		for(UserModel userModel : userDAOBean.findByCountry(searchWord)){
			dropModelList.addAll(userModel.getDrops());
		}
		return dropModelList;
		
	}

}
