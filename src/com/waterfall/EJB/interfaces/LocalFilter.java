package com.waterfall.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.waterfall.models.DropModel;

@Local
public interface LocalFilter {
	
	List<DropModel> filterDrops(String[] tagArray);

	List<DropModel> filterByGender(boolean filterByMale, boolean filterByFemale, boolean filterByOther);

	List<DropModel> filterByAgeSpan(int startAge, int endAge);

}
