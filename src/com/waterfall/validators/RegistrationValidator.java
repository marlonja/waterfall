package com.waterfall.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.waterfall.DAO.UserDAOBean;
import com.waterfall.models.User;

@Stateful
public class RegistrationValidator {
	
	private String regexOnlyLetter = "^[-A-ZÅÄÖa-zåäö]+$";
	private String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private boolean validationSuccess = true;
	@EJB
	private UserDAOBean userDAOBean;
	
	public boolean validateUserForRegistration(User userToValidate){
	
		validateCorrectFormat(userToValidate.getFirstname());
		validateCorrectFormat(userToValidate.getLastname());
		validateCorrectFormat(userToValidate.getCity());
		validateCorrectEmailFormat(userToValidate.getEmail());
		validateUniqueEmail(userToValidate.getEmail());
		
		return validationSuccess;
		
	}
	
	private void validateCorrectFormat(String userInput){
		System.out.println("I validateUserFirstName");
		Pattern pattern = Pattern.compile(regexOnlyLetter);
		Matcher matcher = pattern.matcher(userInput);
		
		if(!userInput.isEmpty()){
			if(!matcher.matches()){
				
				validationSuccess = false;
			
			}
		}
	}
	private void validateCorrectEmailFormat(String userEmail){
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(userEmail);
		
		if(!userEmail.isEmpty()){
			if(!matcher.matches()){
				
				validationSuccess = false;
			}
			
		}
		
	}
	
	private void validateUniqueEmail(String userEmail){
		validationSuccess = userDAOBean.isEmailUnique(userEmail);
	
	}
	
}
