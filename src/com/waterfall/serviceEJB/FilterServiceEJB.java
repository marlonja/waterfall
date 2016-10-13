package com.waterfall.serviceEJB;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalFilter;
import com.waterfall.models.DropModel;
import com.waterfall.models.UserModel;
import com.waterfall.storage.DropDAOBean;
import com.waterfall.storage.UserDAOBean;
import com.waterfall.utils.DateService;

@Stateless
public class FilterServiceEJB implements LocalFilter {

	@EJB
	DropDAOBean dropDAOBean;

	@EJB
	UserDAOBean userDAOBean;

	private ArrayList<DropModel> dropListFromSearch;

	@EJB
	DateService dateService;

	private boolean filterByMale;
	private boolean filterByFemale;
	private boolean filterByOther;

	@Override
	public List<DropModel> filterDrops(String[] searchWords, boolean filteredByMale, boolean filteredByFemale,
			boolean filteredByOther) {

		filterByMale = filteredByMale;
		filterByFemale = filteredByFemale;
		filterByOther = filteredByOther;

		dropListFromSearch = (ArrayList<DropModel>) getInitialList(searchWords);

		dropListFromSearch = (ArrayList<DropModel>) removeDuplicatesFromSearchList();

		return dropListFromSearch;

	}


	@SuppressWarnings("deprecation")
	@Override
	public List<DropModel> filterByAgeSpan(int startAge, int endAge) {
		
		if(dropListFromSearch == null) {
			dropListFromSearch = new ArrayList<DropModel>();
		}
		
		dropListFromSearch.clear();
		List<UserModel> listOfUserstest = new ArrayList<UserModel>();
		List<DropModel> listofDropsFilteredByAge = new ArrayList<DropModel>();
		
		int month = dateService.getCurrentDate().getMonthValue();
		int day = dateService.getCurrentDate().getDayOfMonth();
		startAge = dateService.getCurrentDate().getYear() - startAge;
		endAge = dateService.getCurrentDate().getYear() - endAge;
		
		Date startDate = new Date(startAge - 1900, month, day);
		Date endDate = new Date(endAge - 1900, month, day);
		
		listOfUserstest.addAll(userDAOBean.getUsersByAge(endDate, startDate));
		
		for(int i = 0; i < listOfUserstest.size(); i++) {
			listofDropsFilteredByAge.addAll(listOfUserstest.get(i).getDrops());
		}
		
		System.out.println(listofDropsFilteredByAge.size());
		dropListFromSearch.addAll(listofDropsFilteredByAge);
		return dropListFromSearch;

}
	public List<DropModel> getInitialList(String[] searchWords) {
		dropListFromSearch = new ArrayList<DropModel>();

		if(filterByMale || filterByFemale || filterByOther) {
		dropListFromSearch = (ArrayList<DropModel>) getDropsByGender();
		}else {
			dropListFromSearch.addAll(dropDAOBean.getAllDrops());
		}
		
		if(!searchWords[0].isEmpty()){
		dropListFromSearch = (ArrayList<DropModel>) filterList(dropListFromSearch, searchWords);
		}

		return (ArrayList<DropModel>) dropListFromSearch;
	}

	private List<DropModel> getDropsByGender() {
		List<UserModel> users = new ArrayList<UserModel>();
		List<DropModel> drops =  new ArrayList<DropModel>();
		if (filterByMale) {
			users.addAll(userDAOBean.getUsersByGender("Male"));
		}

		if (filterByFemale) {
			users.addAll(userDAOBean.getUsersByGender("Female"));
		}

		if (filterByOther) {
			users.addAll(userDAOBean.getUsersByGender("Other"));
		}
		
		for (UserModel user : users) {
			drops.addAll(user.getDrops());
		}

		return (ArrayList<DropModel>) drops;

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

		for (int i = 0; i < dropListFromSearch.size(); i++) {
			if (!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("male")) {
				dropListFromSearch.remove(i);
			}
		}

		return dropListFromSearch;
	}

	private List<DropModel> filterByFemale() {

		for (int i = 0; i < dropListFromSearch.size(); i++) {
			if (!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("female")) {
				dropListFromSearch.remove(dropListFromSearch.get(i));
			}
		}

		return dropListFromSearch;
	}

	private List<DropModel> filterByOther() {

		for (int i = 0; i < dropListFromSearch.size(); i++) {
			if (!dropListFromSearch.get(i).getOwner().getGender().equalsIgnoreCase("other")) {
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

	private List<DropModel> filterList(ArrayList<DropModel> dropListFromSearch, String[] searchWords) {

		List<DropModel> filteredList = new ArrayList<DropModel>();
		for (int i = 0; i < dropListFromSearch.size(); i++) {
			boolean dropContainsAllWords = dropContainsAllSearchWords(dropListFromSearch.get(i), searchWords);

			if (dropContainsAllWords) {
				filteredList.add(dropListFromSearch.get(i));
			}

		}

		return filteredList;
	}

	private boolean userInformationContainsSearchWords(UserModel owner, String searchWord) {

		boolean containsSearchWord = false;

		if (containsSearchWord(owner, searchWord)) {
			containsSearchWord = true;
		}

		return containsSearchWord;

	}

	private boolean containsSearchWord(UserModel user, String searchWord) {

		String userInfo = user.getFirstName() + user.getLastName() + user.getUsername() + user.getCity()
				+ user.getCountry();

		if (userInfo.toLowerCase().contains(searchWord.toLowerCase())) {
			return true;
		}

		return false;
	}

	private boolean dropContainsAllSearchWords(DropModel drop, String[] searchWords) {

		for (int i = 0; i < searchWords.length; i++) {
			if (!drop.getContent().toLowerCase().contains(searchWords[i].toLowerCase())
					&& !userInformationContainsSearchWords(drop.getOwner(), searchWords[i])) {

				return false;
			}
		}
		return true;

	}

}
