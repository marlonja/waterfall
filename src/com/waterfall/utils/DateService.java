package com.waterfall.utils;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

@Stateful
public class DateService {
	
	public List<Integer> years() {
		List<Integer> yearList = new ArrayList<Integer>();
		for(int i = 1900; i <= 2016; i++) {
			yearList.add(i);
		}
		return yearList;
	}

}
