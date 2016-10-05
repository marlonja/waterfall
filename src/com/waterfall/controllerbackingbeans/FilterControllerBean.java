package com.waterfall.controllerbackingbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value="filterControllerBean")
@RequestScoped
public class FilterControllerBean {
	
	private boolean isFilteredByMale;
	private boolean isFilteredByFemale;
	private boolean isFilteredByOther;
	
	public boolean isFilteredByMale() {
		return isFilteredByMale;
	}
	public void setFilteredByMale(boolean isFilteredByMale) {
		this.isFilteredByMale = isFilteredByMale;
	}
	public boolean isFilteredByFemale() {
		return isFilteredByFemale;
	}
	public void setFilteredByFemale(boolean isFilteredByFemale) {
		this.isFilteredByFemale = isFilteredByFemale;
	}
	public boolean isFilteredByOther() {
		return isFilteredByOther;
	}
	public void setFilteredByOther(boolean isFilteredByOther) {
		this.isFilteredByOther = isFilteredByOther;
	}
	
	
	
}
