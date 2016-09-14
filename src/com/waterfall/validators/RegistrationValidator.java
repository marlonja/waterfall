package com.waterfall.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.waterfall.DAO.UserDAOBean;
import com.waterfall.models.User;

@Stateful
public class RegistrationValidator {
	
	private String regexOnlyLetter = "^[-A-Z���a-z���]+$";
	private String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private boolean validationSuccess = true;
	@EJB
	private UserDAOBean userDAOBean;
	
	public boolean validateUserForRegistration(User userToValidate){
	
		validateCorrectFormat(userToValidate.getFirstname());
		validateCorrectFormat(userToValidate.getLastname());
		validateCorrectFormat(userToValidate.getCity());
		if (validateCorrectEmailFormat(userToValidate.getEmail())) {
			System.out.println("Email format is okey, inne i if:en");
			validateUniqueEmail(userToValidate.getEmail());
		}
		System.out.println(validationSuccess);
		return validationSuccess;
		
	}
	
	private void validateCorrectFormat(String userInput){
		System.out.println("Validerar korrekt format för inputs...");
		Pattern pattern = Pattern.compile(regexOnlyLetter);
		Matcher matcher = pattern.matcher(userInput);
		
		if(!userInput.isEmpty()){
			System.out.println("Inputfältet är ifyllt...");
			if(!matcher.matches()){
				System.out.println("Inputfältet är ifyllt men av fel format...");
				System.out.println("Testar");
				validationSuccess = false;
			}
		}
	}
	private boolean validateCorrectEmailFormat(String userEmail){
		System.out.println("Validating email format.....");
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(userEmail);
		
		if(!userEmail.isEmpty()){
			System.out.println("Email är ifyllt...");
			if(matcher.matches()){
				System.out.println("Emailen har rätt format...");
				validationSuccess = true;
				return true;
			}
			System.out.println("Email är ifyllt men har fel format...");
		}
		validationSuccess = false;
		return false;
		
	}
	
	private void validateUniqueEmail(String userEmail){
		validationSuccess = userDAOBean.isEmailUnique(userEmail);
	
	}
	
}
