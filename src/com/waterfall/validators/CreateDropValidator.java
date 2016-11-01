package com.waterfall.validators;

import javax.ejb.Stateless;

import com.waterfall.models.DropModel;

@Stateless
public class CreateDropValidator {

	public boolean validateDrop(DropModel dropModel) {
		if(validateDropContent(dropModel.getContent())){
			return true;
		}
		return false;
		
	}
	
	private boolean validateDropContent(String content){
		if(content.trim().equals("") || content.length() > 200){
			return false;
		}
		
		return true;
	}
	

}
