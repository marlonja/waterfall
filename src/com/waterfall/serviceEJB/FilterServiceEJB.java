package com.waterfall.serviceEJB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.waterfall.EJB.interfaces.LocalFilter;
import com.waterfall.controllerbackingbeans.DropControllerBean;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.storage.DropDAOBean;
import com.waterfall.storage.UserDAOBean;

@Stateless
public class FilterServiceEJB implements LocalFilter {

	@EJB
	DropDAOBean dropDAOBean;

	@EJB
	UserDAOBean userDAOBean;

	private ArrayList<DropModel> dropListFromSearch;

	@Override
	public List<DropModel> filterDrops(String[] tagArray) {
		dropListFromSearch = new ArrayList<DropModel>();

		for (int i = 0; i < tagArray.length; i++) {

			dropListFromSearch.addAll(dropDAOBean.searchDropsFromDropTable(tagArray[i]));
			for (UserModel userModel : userDAOBean.searchDropsFromUserTable(tagArray[i])) {
				dropListFromSearch.addAll(userModel.getDrops());
			}
			
			if(i > 0){
				dropListFromSearch = (ArrayList<DropModel>) filterList(dropListFromSearch, tagArray[i]);
			}
		}

		dropListFromSearch = (ArrayList<DropModel>) removeDuplicatesFromSearchList(dropListFromSearch);
		
		return dropListFromSearch;
		
	}

	private List<DropModel> filterList(ArrayList<DropModel> dropListFromSearch, String currentWord) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		for(int i = 0; i < dropListFromSearch.size(); i++){
			if(dropListFromSearch.get(i).getContent().contains(currentWord)){
				System.out.println("Ja jättebra!!! " + dropListFromSearch.get(i).getContent());
				filteredList.add(dropListFromSearch.get(i));
			}
		}
		return filteredList;
	}

	private List<DropModel> removeDuplicatesFromSearchList(ArrayList<DropModel> dropListFromSearch) {
		for (int i = 0; i < dropListFromSearch.size(); i++) {
			for (int j = i + 1; j < dropListFromSearch.size(); j++) {
				if (dropListFromSearch.get(i).getDropId().equals(dropListFromSearch.get(j).getDropId())) {
					dropListFromSearch.remove(j);
					j = j - 1;
				}
			}
		}
		return dropListFromSearch;
	}

}
