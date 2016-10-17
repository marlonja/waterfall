package com.waterfall.controllerbackingbeans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalFilter;
import com.waterfall.models.FilterModel;
import com.waterfall.utils.DateService;

@Named(value = "filterControllerBean")
@RequestScoped
public class FilterControllerBean {

	private boolean filteredByMale;
	private boolean filteredByFemale;
	private boolean filteredByOther;
	private int startAge;
	private int endAge;
	private String tagList;
	private List<Integer> ageList;
	private String filterFirstName;
	private String ageSpan;

	@PostConstruct
	public void init() {
		setAgeList(dateService.ageList());
	}
	
	@EJB
	DateService dateService;

	@Inject
	DropControllerBean dropControllerBean;

	@EJB
	LocalFilter filterServiceEJB;
	
	private FilterModel createNewFilter() {
		FilterModel filterModel = new FilterModel();
		
		String[] ageSpanList = ageSpan.split("-");
		startAge = Integer.parseInt(ageSpanList[0].trim());
		endAge = Integer.parseInt(ageSpanList[1].trim());
		
		filterModel.setAgeSpanEndAge(endAge);
		filterModel.setAgeSpanStartAge(startAge);
		filterModel.setFilterByFemale(filteredByFemale);
		filterModel.setFilterByMale(filteredByMale);
		filterModel.setFilterByOther(filteredByOther);
		filterModel.setFilterFirstName(filterFirstName);
		
		String[] searchWords = tagList.split(",");
		filterModel.setSearchWords(searchWords);
		
		return filterModel;
		
	}
	
	public String filter() {
		FilterModel filterModel = createNewFilter();
		
		dropControllerBean.setDropList(filterServiceEJB.filterDrops(filterModel));
		
		return "index";
	}

//	public String filterByAge() {
//		System.out.println("i controllerbean: " + startAge + " " + endAge);
//		filterServiceEJB.filterByAgeSpan(startAge, endAge);
//		return "index";
//	}


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
	
	public List<Integer> getAgeList() {
		return ageList;
	}

	public void setAgeList(List<Integer> ageList) {
		this.ageList = ageList;
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

}
