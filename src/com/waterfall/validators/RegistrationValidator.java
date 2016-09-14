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

		if (!validateCorrectBasicFormat(userToValidate)) {
			return false;
		}

		if (validateCorrectEmailFormat(userToValidate.getEmail()) && validateUniqueEmail(userToValidate.getEmail())) {
			return true;
		} else {
			return false;
		}

	}

	private boolean validateCorrectBasicFormat(User userToValidate) {

		if (!validateOnlyLetters(userToValidate.getFirstname())) {
			return false;
		}

		if (!validateOnlyLetters(userToValidate.getLastname())) {
			return false;
		}

		if (!validateOnlyLetters(userToValidate.getCity())) {
			return false;
		}

		return true;
	}

	private boolean validateOnlyLetters(String userInput) {
		Pattern pattern = Pattern.compile(regexOnlyLetter);
		Matcher matcher = pattern.matcher(userInput);

		if (!userInput.isEmpty()) {
			if (!matcher.matches()) {
				return false;

			}
		}
		return true;
	}

	private boolean validateCorrectEmailFormat(String userEmail) {
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

	private boolean validateUniqueEmail(String userEmail) {
		return userDAOBean.isEmailUnique(userEmail);

	}

}
