package com.waterfall.models;

public class FilterModel {
	
	private String[] searchWords;
	private Boolean filterByMale;
	private Boolean filterByFemale;
	private Boolean filterByOther;
	private Integer ageSpanStartDate;
	private Integer ageSpanEndDate;
	private String filterFirstName;
	private String filterLastName;
	private String filterUsername;
	private String filterCity;
	private String filterCountry;

	public String[] getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(String[] searchWords) {
		this.searchWords = searchWords;
	}

	public Boolean isFilterByMale() {
		return filterByMale;
	}

	public void setFilterByMale(Boolean filterByMale) {
		this.filterByMale = filterByMale;
	}

	public Boolean isFilterByFemale() {
		return filterByFemale;
	}

	public void setFilterByFemale(Boolean filterByFemale) {
		this.filterByFemale = filterByFemale;
	}

	public Boolean isFilterByOther() {
		return filterByOther;
	}

	public void setFilterByOther(Boolean filterByOther) {
		this.filterByOther = filterByOther;
	}

	public Integer getAgeSpanStartDate() {
		return ageSpanStartDate;
	}

	public void setAgeSpanStartAge(Integer ageSpanStartDate) {
		this.ageSpanStartDate = ageSpanStartDate;
	}

	public Integer getAgeSpanEndDate() {
		return ageSpanEndDate;
	}

	public void setAgeSpanEndAge(Integer ageSpanEndDate) {
		this.ageSpanEndDate = ageSpanEndDate;
	}

	public String getFilterFirstName() {
		return filterFirstName;
	}

	public void setFilterFirstName(String filterFirstName) {
		this.filterFirstName = filterFirstName;
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
	
	

}