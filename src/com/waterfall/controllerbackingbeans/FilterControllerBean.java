package com.waterfall.controllerbackingbeans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalContactList;
import com.waterfall.EJB.interfaces.LocalFilter;
import com.waterfall.EJB.interfaces.LocalUser;
import com.waterfall.models.ContactListModel;
import com.waterfall.models.FilterModel;
import com.waterfall.serviceEJB.ContactListServiceEJB;
import com.waterfall.serviceEJB.UserServiceEJB;
import com.waterfall.utils.DateService;

@Named(value = "filterControllerBean")
@RequestScoped
public class FilterControllerBean {

	private boolean filteredByFemale;
	private boolean filteredByMale;
	private boolean filteredByOther;
	private String filterFirstName;
	private String filterLastName;
	private String filterUsername;
	private String filterCity;
	private String filterCountry;
	private String tagList;
	private String ageSpan;
	private String poolname;
	private int startAge;
	private int endAge;
	private List<FilterModel> filterList;

	@EJB
	DateService dateService;

	@Inject
	DropControllerBean dropControllerBean;

	@EJB
	LocalFilter filterServiceEJB;
	
	@EJB
	LocalContactList contactListServiceEJB;
	
	@EJB
	LocalUser userServiceEJB;
	
	public String setFilters(Long filterid) {
		FilterModel filterModel = filterServiceEJB.getFilterById(filterid);
		dropControllerBean.setDropList(filterServiceEJB.filterDrops(filterModel));
		
		return "index";
	}
	
	public String filterByContacts(ContactListModel contactListModel){
		dropControllerBean.setDropList(contactListServiceEJB.getContactDrops(contactListModel));
		return "index";
	}
	
	public String saveFilterAsPool() {
		FilterModel filterModel = createNewFilter();
		filterServiceEJB.saveFilterAsPool(filterModel);
		filterList = userServiceEJB.getUser(userServiceEJB.getUserFromSession("loggedInUser").getUserid()).getFilterList();
		return "index";
	}
	
	private FilterModel createNewFilter() {
		FilterModel filterModel = new FilterModel();
		
		String[] ageSpanList = ageSpan.split("-");
		startAge = Integer.parseInt(ageSpanList[0].trim());
		endAge = Integer.parseInt(ageSpanList[1].trim());
		
		filterModel.setEndAge(endAge);
		filterModel.setStartAge(startAge);
		filterModel.setIsFilteredByFemale(filteredByFemale);
		filterModel.setIsFilteredByMale(filteredByMale);
		filterModel.setIsFilteredByOther(filteredByOther);
		filterModel.setFirstName(filterFirstName);
		filterModel.setLastName(filterLastName);
		filterModel.setUsername(filterUsername);
		filterModel.setCity(filterCity);
		filterModel.setCountry(filterCountry);
		filterModel.setSearchWords(tagList);
		filterModel.setPoolname(poolname);
		
		filterModel.setFilterowner(userServiceEJB.getUserFromSession("loggedInUser"));
		
		return filterModel;
	}
	
	public String filter() {
		FilterModel filterModel = createNewFilter();
		
		dropControllerBean.setDropList(filterServiceEJB.filterDrops(filterModel));		
		
		return "index";
	}
	
	public String clearFilterInputFields(){
		dropControllerBean.init();
		
		filterFirstName = null;
		filterLastName = null;
		filterUsername = null;
		filterCity = null;
		filterCountry = null;
		filteredByMale = false;
		filteredByFemale = false;
		filteredByOther = false;
		tagList = null;
		return "index";
	}

	public boolean isFilteredByMale() {
		return filteredByMale;
	}

	public void setFilteredByMale(boolean isFilteredByMale) {
		this.filteredByMale = isFilteredByMale;
	}

	public boolean isFilteredByFemale() {
		return filteredByFemale;
	}

	public void setFilteredByFemale(boolean isFilteredByFemale) {
		this.filteredByFemale = isFilteredByFemale;
	}

	public boolean isFilteredByOther() {
		return filteredByOther;
	}

	public void setFilteredByOther(boolean isFilteredByOther) {
		this.filteredByOther = isFilteredByOther;
	}

	public String getTagList() {
		return tagList;
	}

	public void setTagList(String tags) {
		this.tagList = tags;
	}

	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}

	public String getFilterFirstName() {
		return filterFirstName;
	}

	public void setFilterFirstName(String filterFirstName) {
		this.filterFirstName = filterFirstName;
	}

	public String getAgeSpan() {
		return ageSpan;
	}

	public void setAgeSpan(String ageSpan) {
		this.ageSpan = ageSpan;
	}

	public String getFilterLastName() {
		return filterLastName;
	}

	public void setFilterLastName(String filterLastName) {
		this.filterLastName = filterLastName;
	}

	public String getFilterUsername() {
		return filterUsername;
	}

	public void setFilterUsername(String filterUsername) {
		this.filterUsername = filterUsername;
	}

	public String getFilterCity() {
		return filterCity;
	}

	public void setFilterCity(String filterCity) {
		this.filterCity = filterCity;
	}

	public String getFilterCountry() {
		return filterCountry;
	}

	public void setFilterCountry(String filterCountry) {
		this.filterCountry = filterCountry;
	}

	public List<FilterModel> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<FilterModel> filterList) {
		this.filterList = filterList;
	}


	public String getPoolname() {
		return poolname;
	}


	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}

}
