package com.waterfall.serviceEJB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.eclipse.persistence.jpa.rs.util.ResourceLocalTransactionWrapper;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.iconType;
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
	public List<DropModel> filterDrops(String[] tagArray, boolean filteredByMale, 
			boolean filteredByFemale, boolean filteredByOther) {
		
		dropListFromSearch = (ArrayList<DropModel>) getInitialList(tagArray);
		filterByGender(filteredByMale, filteredByFemale, filteredByOther);
		dropListFromSearch = (ArrayList<DropModel>) removeDuplicatesFromSearchList(dropListFromSearch);

		return dropListFromSearch;

	}
	
	public List<DropModel> getInitialList(String[] tagArray) {
		dropListFromSearch = new ArrayList<DropModel>();
		
		for (int i = 0; i < tagArray.length; i++) {

			dropListFromSearch.addAll(dropDAOBean.findDropsByContent(tagArray[i]));
			
//			for (UserModel userModel : userDAOBean.searchDropsFromUserTable(tagArray[i])) {
//				dropListFromSearch.addAll(userModel.getDrops());
//			}

			dropListFromSearch = (ArrayList<DropModel>) filterList(dropListFromSearch, tagArray);

		}
		
		return dropListFromSearch;
	}

	public List<DropModel> filterByGender(boolean filteredByMale, boolean filteredByFemale, boolean filteredByOther) {
		if (filteredByMale) {
			filterByMale();
		}
		if (filteredByFemale) {
			filterByFemale();
		}
		if (filteredByOther) {
			filterByOther();
		}

		return dropListFromSearch;
	}

	private void filterByMale() {
		
		for(int i = 0; i < dropListFromSearch.size(); i++) {
			if(!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("male")){
				dropListFromSearch.remove(i);
				System.out.println("Nu var det inte en man, tog bort!");
			}
		}
	}

	private void filterByFemale() {
		
		for(int i = 0; i < dropListFromSearch.size(); i++) {
			if(!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("female")){
				dropListFromSearch.remove(dropListFromSearch.get(i));
				System.out.println("Nu var det inte en kvinna, tog bort!");
			}
		}
	}

	private void filterByOther() {
		
		for(int i = 0; i < dropListFromSearch.size(); i++) {
			if(!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("other")){
				dropListFromSearch.remove(i);
				System.out.println("Nu var det inte en annan typ, tog bort!");
			}
		}
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

	private List<DropModel> filterList(ArrayList<DropModel> dropListFromSearch,  String[] tagArray) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		for (int i = 0; i < dropListFromSearch.size(); i++) {
			if(dropContainsAllSearchWords(dropListFromSearch.get(i), tagArray)) {
				filteredList.add(dropListFromSearch.get(i));
			}
		}
		return filteredList;
	}
	
	private boolean dropContainsAllSearchWords(DropModel drop, String[] tagArray) {
		
		for(int i = 0; i < tagArray.length; i++) {
			if(!drop.getContent().contains(tagArray[i])){
				System.out.println("dropmodel: " + drop.getContent() + "innehöll inte: " + tagArray[i]);
				return false;
			}
		}
		
//		System.out.println("dropmodel: " + drop.getContent() + "innehöll: ");
//		for(int i = 0; i < tagArray.length; i++) {
//			System.out.println(tagArray[i]);
//		}
		return true;

	}

}
