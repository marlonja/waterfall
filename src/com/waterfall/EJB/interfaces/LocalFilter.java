package com.waterfall.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.DropModel;
import com.waterfall.models.FilterModel;

@Local
public interface LocalFilter {
	
	List<DropModel> filterDrops(FilterModel filterModel);

	List<DropModel> filterByAgeSpan(int startAge, int endAge);

	void saveFilterAsPool(FilterModel filterModel);

	List<FilterModel> getAllFilters();

	FilterModel getFilterById(Long filterid);

}
