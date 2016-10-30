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
import com.waterfall.storage.FilterDAOBean;
import com.waterfall.storage.UserDAOBean;
import com.waterfall.utils.DateService;

@Stateless
public class FilterServiceEJB implements LocalFilter {

	@EJB
	DropDAOBean dropDAOBean;

	@EJB
	UserDAOBean userDAOBean;
	
	@EJB
	FilterDAOBean filterDAOBean;

	@EJB
	DateService dateService;

	private ArrayList<DropModel> dropListFromSearch;
	private boolean filterByMale;
	private boolean filterByFemale;
	private boolean filterByOther;
	private int startAge;
	private int endAge;
	private String filterByFirstName;
	private String filterByLastName;
	private String filterByUsername;
	private String filterByCity;
	private String filterByCountry;
	
	
	@Override
	public FilterModel getFilterById(Long filterid) {
		return filterDAOBean.getFilterById(filterid);
	}
	
	@Override
	public List<FilterModel> getAllFilters() {
		
		return filterDAOBean.getAllFilters();
	}
	
	@Override
	public void saveFilterAsPool(FilterModel filterModel) {
		filterDAOBean.storeFilterAsPool(filterModel);
	}

	@Override
	public List<DropModel> filterDrops(FilterModel filterModel) {

		filterByMale = filterModel.getIsFilteredByMale();
		filterByFemale = filterModel.getIsFilteredByFemale();
		filterByOther = filterModel.getIsFilteredByOther();
		startAge = filterModel.getStartAge();
		endAge = filterModel.getEndAge();
		filterByFirstName = filterModel.getFirstName();
		filterByLastName = filterModel.getLastName();
		filterByUsername = filterModel.getUsername();
		filterByCity = filterModel.getCity();
		filterByCountry = filterModel.getCountry();
		
		dropListFromSearch = (ArrayList<DropModel>) getInitialList(splitTagList(filterModel));
		dropListFromSearch = (ArrayList<DropModel>) removeDuplicatesFromSearchList();

		return dropListFromSearch;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<DropModel> filterByAgeSpan(int startAge, int endAge) {

		if (dropListFromSearch == null) {
			dropListFromSearch = new ArrayList<DropModel>();
		}
		int month = dateService.getCurrentDate().getMonthValue() - 1;
		int day = dateService.getCurrentDate().getDayOfMonth();
		startAge = dateService.getCurrentDate().getYear() - startAge;
		endAge = dateService.getCurrentDate().getYear() - endAge;

		Date endDate = new Date(startAge - 1900, month, day);
		Date startDate = new Date(endAge - 1900, month, day);
		System.out.println("enddate: " + endDate);
		System.out.println("startdate " + startDate);

		for (int i = 0; i < dropListFromSearch.size(); i++) {
			int birthYear = dropListFromSearch.get(i).getOwner().getBirthdate().getYear();
			int birthMonth = dropListFromSearch.get(i).getOwner().getBirthdate().getMonth();
			int birthDay = dropListFromSearch.get(i).getOwner().getBirthdate().getDate();

			Date userBirthDate = new Date(birthYear, birthMonth, birthDay);
			if (userBirthDate.before(startDate) || userBirthDate.after(endDate)) {
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

		if (!(startAge == 0 && endAge == 0) && !(startAge > endAge)) {

			dropListFromSearch = (ArrayList<DropModel>) filterByAgeSpan(startAge, endAge);
		}
		if (!searchWords[0].isEmpty()) {
			dropListFromSearch = (ArrayList<DropModel>) filterList(dropListFromSearch, searchWords);
		}

		if (!filterByFirstName.isEmpty()) {
			dropListFromSearch = (ArrayList<DropModel>) filterByFirstName(dropListFromSearch, filterByFirstName);
		}

		if (!filterByLastName.isEmpty()) {
			dropListFromSearch = (ArrayList<DropModel>) filterByLastName(dropListFromSearch, filterByLastName);
		}

		if (!filterByUsername.isEmpty()) {
			dropListFromSearch = (ArrayList<DropModel>) filterByUsername(dropListFromSearch, filterByUsername);
		}

		if (!filterByCity.isEmpty()) {
			dropListFromSearch = (ArrayList<DropModel>) filterByCity(dropListFromSearch, filterByCity);
		}

		if (!filterByCountry.isEmpty()) {
			dropListFromSearch = (ArrayList<DropModel>) filterByCountry(dropListFromSearch, filterByCountry);
		}

		return (ArrayList<DropModel>) dropListFromSearch;
	}

	private List<DropModel> filterByCountry(ArrayList<DropModel> dropListFromSearch,String country) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		
		for(DropModel drop : dropListFromSearch) {
			if(drop.getOwner().getCountry().equalsIgnoreCase(country)) {
				filteredList.add(drop);
			}
		}
		return filteredList;
	}

	private List<DropModel> filterByCity(ArrayList<DropModel> dropListFromSearch, String city) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		
		for(DropModel drop : dropListFromSearch) {
			if(drop.getOwner().getCity().equalsIgnoreCase(city)) {
				filteredList.add(drop);
			}
		}
		return filteredList;
	}

	private List<DropModel> filterByUsername(ArrayList<DropModel> dropListFromSearch, String username) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		for(DropModel drop : dropListFromSearch) {
			if(drop.getOwner().getUsername().equalsIgnoreCase(username)) {
				filteredList.add(drop);
			}
		}
		return filteredList;
	}

	private List<DropModel> filterByLastName(ArrayList<DropModel> dropListFromSearch, String lastName) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		
		for(DropModel drop : dropListFromSearch) {
			if(drop.getOwner().getLastName().equalsIgnoreCase(lastName)) {
				filteredList.add(drop);
			}
		}
		return filteredList;
	}

	private List<DropModel> filterByFirstName(List<DropModel> dropListFromSearch, String firstName) {
		List<DropModel> filteredList = new ArrayList<DropModel>();
		
		for(DropModel drop : dropListFromSearch) {
			if(drop.getOwner().getFirstName().equalsIgnoreCase(firstName)) {
				filteredList.add(drop);
			}
		}
		return filteredList;
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
	
	private String[] splitTagList(FilterModel filterModel) {
		String[] searchWords = filterModel.getSearchWords().split(",");
		return searchWords;
	}

	

	

	
}
