package com.waterfall.controllerbackingbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.waterfall.EJB.interfaces.LocalFilter;
import com.waterfall.models.DropModel;
import com.waterfall.storage.DropDAOBean;

@Named(value="filterControllerBean")
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



	public void setTagList(String tagList) {
		String[] tagArray = tagList.split(",");
		dropControllerBean.setDropList(filterServiceEJB.filterDrops(tagArray));
		this.tagList = tagList;
	}
	
	
	
}
