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
			System.out.println("Username exists vi are i validation");
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

	private boolean isContainingOnlyLetters(String userInput) {
		Pattern pattern = Pattern.compile(regexOnlyLetter);
		Matcher matcher = pattern.matcher(userInput);

		if (!userInput.isEmpty()) {
			if (!matcher.matches()) {
				return false;

			}
		}
		return true;
	}

	private boolean isEmailFormatCorrect(String userEmail) {
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(userEmail);

		if (!userEmail.isEmpty()) {
			System.out.println("email inte tom");
			if (!matcher.matches()) {
				return false;
			}

		}
		return true;
	}

	private boolean isEmailUnique(String userEmail) {
		return userDAOBean.isEmailInDatabaseUnique(userEmail);
	}
	
	private boolean isUsernameUnique(String username){
		return userDAOBean.isUsernameInDatabaseUnique(username);
	}
	

}
