package com.waterfall.serviceEJB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonParser;

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
	public List<DropModel> filterDrops(String[] searchWords, boolean filteredByMale, 
			boolean filteredByFemale, boolean filteredByOther) {
		
		dropListFromSearch = (ArrayList<DropModel>) getInitialList(searchWords);
		dropListFromSearch = (ArrayList<DropModel>) filterByGender(filteredByMale, filteredByFemale, filteredByOther);
		dropListFromSearch = (ArrayList<DropModel>) removeDuplicatesFromSearchList();

		return dropListFromSearch;

	}
	
	public List<DropModel> getInitialList(String[] searchWords) {
		dropListFromSearch = new ArrayList<DropModel>();
		
		for (int i = 0; i < searchWords.length; i++) {

			dropListFromSearch.addAll(dropDAOBean.findDropsByContent(searchWords[i]));
			
			for (UserModel userModel : userDAOBean.searchDropsFromUserTable(searchWords[i])) {
				dropListFromSearch.addAll(userModel.getDrops());
			}
		}
		
		dropListFromSearch = (ArrayList<DropModel>) filterList(dropListFromSearch, searchWords);
		
		return dropListFromSearch;
	}

	public List<DropModel> filterByGender(boolean filteredByMale, boolean filteredByFemale, boolean filteredByOther) {
		if (filteredByMale) {
			dropListFromSearch = (ArrayList<DropModel>) filterByMale();
		}
		if (filteredByFemale) {
			dropListFromSearch = (ArrayList<DropModel>) filterByFemale();
		}
		if (filteredByOther) {
			dropListFromSearch = (ArrayList<DropModel>) filterByOther();
		}

		return dropListFromSearch;
	}

	private List<DropModel> filterByMale() {
		
		for(int i = 0; i < dropListFromSearch.size(); i++) {
			if(!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("male")){
				dropListFromSearch.remove(i);
			}
		}
		
		return dropListFromSearch;
	}

	private List<DropModel> filterByFemale() {
		
		for(int i = 0; i < dropListFromSearch.size(); i++) {
			if(!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("female")){
				dropListFromSearch.remove(dropListFromSearch.get(i));
			}
		}
		
		return dropListFromSearch;
	}

	private List<DropModel> filterByOther() {
		
		for(int i = 0; i < dropListFromSearch.size(); i++) {
			if(!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("other")){
				dropListFromSearch.remove(i);
			}
		}
		
		return dropListFromSearch;
	}

	private List<DropModel> removeDuplicatesFromSearchList() {
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

	private List<DropModel> filterList(ArrayList<DropModel> dropListFromSearch,  String[] searchWords) {
		
		List<DropModel> filteredList = new ArrayList<DropModel>();
		for (int i = 0; i < dropListFromSearch.size(); i++) {
			boolean dropContainsAllWords = dropContainsAllSearchWords(dropListFromSearch.get(i), searchWords);
			
			if(dropContainsAllWords) {
				filteredList.add(dropListFromSearch.get(i));
			}
			
		}
		
		return filteredList;
	}
	
	private boolean userInformationContainsSearchWords(UserModel owner, String searchWord) {
		
		boolean containsSearchWord = false;
		
		if(containsSearchWord(owner, searchWord)){
			containsSearchWord = true;
		}
		
		if(containsSearchWord) {
			System.out.println("Droppens �gare hade ordet: ");
			System.out.println(searchWord);
		}
		
		return containsSearchWord;
		

	}
	
	private boolean containsSearchWord(UserModel user, String searchWord) {
		
		String userInfo = user.getFirstName() + user.getLastName() + user.getUsername() + user.getCity() + user.getCountry();
		
		System.out.println(userInfo);
		
		if(userInfo.toLowerCase().contains(searchWord.toLowerCase())){
			return true;
		}
		
		return false;
	}
	
	private boolean dropContainsAllSearchWords(DropModel drop, String[] searchWords) {
		
		for(int i = 0; i < searchWords.length; i++) {
			if(!drop.getContent().toLowerCase().contains(searchWords[i].toLowerCase()) && !userInformationContainsSearchWords(drop.getOwner(), searchWords[i])){
				
//				System.out.println("Att dropmodel med content: " + drop.getContent() +
//						"inneh�ll ordet: " + searchWords[i] +
//						" var " + drop.getContent().toLowerCase().contains(searchWords[i].toLowerCase()));
//				System.out.println("Att dropmodel med username: " + drop.getOwner().getUsername() +
//						"inneh�ll ordet: " + searchWords[i] +
//						" var " + userInformationContainsSearchWords(drop.getOwner(), searchWords[i]));
				return false;
			}else {
//				System.out.println("Att dropmodel med content: " + drop.getContent() +
//						"inneh�ll ordet: " + searchWords[i] +
//						" var " + drop.getContent().toLowerCase().contains(searchWords[i].toLowerCase()));
//				System.out.println("Att dropmodel med username: " + drop.getOwner().getUsername() +
//						"inneh�ll ordet: " + searchWords[i] +
//						" var " + userInformationContainsSearchWords(drop.getOwner(), searchWords[i]));
			}
		}
		return true;

	}

}
