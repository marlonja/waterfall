package com.waterfall.serviceEJB;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.waterfall.EJB.interfaces.LocalFilter;
import com.waterfall.models.DropModel;
import com.waterfall.models.FilterModel;
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
	private int startAge;
	private int endAge;

	@Override
	public List<DropModel> filterDrops(FilterModel filterModel) {

		this.filterByMale = filterModel.isFilterByMale();
		this.filterByFemale = filterModel.isFilterByFemale();
		this.filterByOther = filterModel.isFilterByOther();
		this.startAge = filterModel.getAgeSpanStartDate();
		this.endAge = filterModel.getAgeSpanEndDate();
		dropListFromSearch = (ArrayList<DropModel>) getInitialList(filterModel.getSearchWords());

		dropListFromSearch = (ArrayList<DropModel>) removeDuplicatesFromSearchList();

		return dropListFromSearch;

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<DropModel> filterByAgeSpan(int startAge, int endAge) {

		if (dropListFromSearch == null) {
			dropListFromSearch = new ArrayList<DropModel>();
		}


		int month = dateService.getCurrentDate().getMonthValue() -1;
		int day = dateService.getCurrentDate().getDayOfMonth();
		startAge = dateService.getCurrentDate().getYear() - startAge;
		endAge = dateService.getCurrentDate().getYear() - endAge;

		Date endDate = new Date(startAge - 1900, month, day);
		Date startDate = new Date(endAge - 1900, month, day);
		System.out.println("enddate: " + endDate);
		System.out.println("startdate " + startDate);
		
		for(int i = 0; i < dropListFromSearch.size(); i++){
			int birthYear = dropListFromSearch.get(i).getOwner().getBirthdate().getYear();
			int birthMonth = dropListFromSearch.get(i).getOwner().getBirthdate().getMonth();
			int birthDay = dropListFromSearch.get(i).getOwner().getBirthdate().getDate();
			
			Date userBirthDate = new Date(birthYear, birthMonth, birthDay);
			if(userBirthDate.before(startDate) || userBirthDate.after(endDate)){
				dropListFromSearch.remove(i);
				i--;
			}
				
		}
		
		return dropListFromSearch;

	}

	private List<DropModel> getInitialList(String[] searchWords) {
		dropListFromSearch = new ArrayList<DropModel>();

		if (filterByMale || filterByFemale || filterByOther) {
			dropListFromSearch = (ArrayList<DropModel>) getDropsByGender();
		} else {
			dropListFromSearch.addAll(dropDAOBean.getAllDrops());
		}
		
		if(!(startAge == 0 && endAge ==0) && !(startAge > endAge)){
			
			dropListFromSearch = (ArrayList<DropModel>) filterByAgeSpan(startAge, endAge);
		}
		if (!searchWords[0].isEmpty()) {
			dropListFromSearch = (ArrayList<DropModel>) filterList(dropListFromSearch, searchWords);
		}

		return (ArrayList<DropModel>) dropListFromSearch;
	}

	private List<DropModel> getDropsByGender() {
		List<UserModel> users = new ArrayList<UserModel>();
		List<DropModel> drops = new ArrayList<DropModel>();
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

	private List<DropModel> removeDuplicatesFromSearchList() {
		for (int i = 0; i < dropListFromSearch.size(); i++) {
			for (int j = i + 1; j < dropListFromSearch.size(); j++) {
				if (dropListFromSearch.get(i).getDropId().equals(dropListFromSearch.get(j).getDropId())) {
					dropListFromSearch.remove(j);
					j--;
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
