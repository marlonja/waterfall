package com.waterfall.controllerbackingbeans;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalFilter;

@Named(value = "filterControllerBean")
@RequestScoped
public class FilterControllerBean {

	private boolean filteredByMale;
	private boolean filteredByFemale;
	private boolean filteredByOther;
	private String tagList;

	@Inject
	DropControllerBean dropControllerBean;

	@EJB
	LocalFilter filterServiceEJB;
	
	public String filter() {
		
		String[] searchWords = tagList.split(",");
		
		dropControllerBean.setDropList(filterServiceEJB.filterDrops(searchWords, filteredByMale, filteredByFemale, filteredByOther));
		
		return "index";
	}

	public String filterByGender() {
		
		System.out.println("Nu kör vi från backingbean, filterByGender");
//		filterServiceEJB.filterByGender(filteredByMale, filteredByFemale, filteredByOther);
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

}
