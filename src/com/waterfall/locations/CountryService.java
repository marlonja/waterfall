package com.waterfall.locations;

import java.util.ArrayList;
import java.util.Locale;

import javax.ejb.Stateful;

@Stateful
public class CountryService {
	
	
	public ArrayList<String> getAllCountries() {
		ArrayList<String> countries = new ArrayList<>();
		String[] countryCodes = Locale.getISOCountries();
		for(int i = 0; i < countryCodes.length; i++) {
			Locale country = new Locale("", countryCodes[i]);
			countries.add(country.getDisplayCountry());
		}
		
		return countries;
	}
}