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
	private String regexOnlyNumbers = "^[0-9-]+$";

	@EJB
	private UserDAOBean userDAOBean;

	public boolean validateUserForRegistration(User userToValidate) {
		
		if (!isBasicFormatCorrect(userToValidate)) {
			return false;
		}
		if(userToValidate.getGender() == null){
			return false;
		}
		if(!isUsernameUnique(userToValidate.getUsername())){
			System.out.println("Username exists vi är i validation");
			return false;
		}
		
		if(userToValidate.getCountry() == null){
			return false;
		}
		
		

		if (isEmailFormatCorrect(userToValidate.getEmail()) && isEmailUnique(userToValidate.getEmail())) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isBasicFormatCorrect(User userToValidate) {

		if (!isContainingOnlyLetters(userToValidate.getFirstName())) {
			return false;
		}

		if (!isContainingOnlyLetters(userToValidate.getLastName())) {
			return false;
		}

		if (!isContainingOnlyLetters(userToValidate.getCity())) {
			return false;
		}

		return true;
	}
	
	private boolean isFormatCorrect(String userInput, String regexPattern){
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(userInput);
		
		if(!matcher.matches()){
			return false;
		}
		return true;
	}

	private boolean isContainingOnlyLetters(String userInput) {
		
		if (userInput.isEmpty()) {
				return false;
		}
		return isFormatCorrect(userInput, regexOnlyLetter);	
		
	}

	private boolean isEmailFormatCorrect(String userEmail) {	

		if (userEmail.isEmpty()) {
			return false;			
		}
		return isFormatCorrect(userEmail, emailRegex);
	}

	private boolean isEmailUnique(String userEmail) {
		return userDAOBean.isEmailInDatabaseUnique(userEmail);
	}
	
	private boolean isUsernameUnique(String username){
		return userDAOBean.isUsernameInDatabaseUnique(username);
	}
	

}
