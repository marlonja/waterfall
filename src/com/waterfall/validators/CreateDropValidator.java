package com.waterfall.validators;

import javax.ejb.Stateless;

import com.waterfall.models.DropModel;

@Stateless
public class CreateDropValidator {

	public boolean validateDrop(DropModel dropModel) {
		System.out.println("inne i validateDrop");
		if(validateDropContent(dropModel.getContent())){
			return true;
		}
		return false;
		
	}
	
	private boolean validateDropContent(String content){
		System.out.println("inne i validatedDropent");
		if(content.equals("") || content.length() > 200){
			System.out.println("droppen är tom eller större än 200 tecken");
			return false;
		}
		
		return true;
	}
	

}
