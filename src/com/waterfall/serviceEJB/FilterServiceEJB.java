package com.waterfall.serviceEJB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public List<DropModel> filterDrops(String[] tagArray) {
		dropListFromSearch = new ArrayList<DropModel>();

		for (int i = 0; i < tagArray.length; i++) {

			dropListFromSearch.addAll(dropDAOBean.searchDropsFromDropTable(tagArray[i]));
			for (UserModel userModel : userDAOBean.searchDropsFromUserTable(tagArray[i])) {
				dropListFromSearch.addAll(userModel.getDrops());
			}

			if (i > 0) {
				dropListFromSearch = (ArrayList<DropModel>) filterList(dropListFromSearch, tagArray[i]);
			}
		}

		dropListFromSearch = (ArrayList<DropModel>) removeDuplicatesFromSearchList(dropListFromSearch);

		return dropListFromSearch;

	}

	@Override
	public void filterByAgeSpan(int startAge, int endAge) {

		List<UserModel> listOfUserstest = new ArrayList<UserModel>();
		SimpleDateFormat birthDate = new SimpleDateFormat("yyyy-MM-dd");

		
		
		startAge = dateService.getCurrentDate().getYear() - startAge;
		endAge = dateService.getCurrentDate().getYear() - endAge;
		
		Date startDate = new Date();
		startDate.setYear(startAge - 1900);
		
		
		
		Date endDate = new Date();
		endDate.setYear(endAge - 1900);
		
		listOfUserstest.addAll(userDAOBean.getUsersByAge(startDate, endDate));

		System.out.println(listOfUserstest);

	}

	public List<DropModel> filterByGender(boolean filteredByMale, boolean filteredByFemale, boolean filteredByOther) {
		dropListFromSearch.clear();
		if (filteredByMale) {
			dropListFromSearch.addAll(filterByMale());
		}
		if (filteredByFemale) {
			dropListFromSearch.addAll(filterByFemale());
		}
		if (filteredByOther) {
			dropListFromSearch.addAll(filterByOther());
		}

		return dropListFromSearch;
	}

	private List<DropModel> filterByMale() {
		List<UserModel> listOfMaleUsers = userDAOBean.getUsersByGender("male");
		List<DropModel> listOfMaleDrops = new ArrayList<DropModel>();

		for (int i = 0; i < listOfMaleUsers.size(); i++) {
			listOfMaleDrops = listOfMaleUsers.get(i).getDrops();
		}
		return listOfMaleDrops;
	}

	private List<DropModel> filterByFemale() {
		List<UserModel> listOfFemaleUsers = userDAOBean.getUsersByGender("female");
		List<DropModel> listOfFemaleDrops = new ArrayList<DropModel>();

		for (int i = 0; i < listOfFemaleUsers.size(); i++) {
			listOfFemaleDrops = listOfFemaleUsers.get(i).getDrops();
		}
		return listOfFemaleDrops;
	}

	private List<DropModel> filterByOther() {
		List<UserModel> listOfOtherUsers = userDAOBean.getUsersByGender("other");
		List<DropModel> listOfOtherDrops = new ArrayList<DropModel>();

		for (int i = 0; i < listOfOtherUsers.size(); i++) {
			listOfOtherDrops = listOfOtherUsers.get(i).getDrops();
		}
		return listOfOtherDrops;
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

	private List<DropModel> filterList(ArrayList<DropModel> dropListFromSearch, String currentWord) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		for (int i = 0; i < dropListFromSearch.size(); i++) {
			if (dropListFromSearch.get(i).getContent().contains(currentWord)) {
				System.out.println("Ja j�ttebra!!! " + dropListFromSearch.get(i).getContent());
				filteredList.add(dropListFromSearch.get(i));
			}
		}
		return filteredList;
	}

}
