package com.waterfall.validators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.sun.el.parser.ParseException;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.ws.security.opt.api.tokens.Timestamp;
import com.waterfall.controllerbackingbeans.RegistrationControllerBean;
import com.waterfall.models.UserModel;
import com.waterfall.storage.UserDAOBean;
import com.waterfall.utils.ErrorMessageService;

@Stateful
public class RegistrationValidator {

	private String regexOnlyLetter = "^[-A-Z���a-z���]+$";
	private String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String regexOnlyNumbers = "^[0-9-]+$";

	@EJB
	private UserDAOBean userDAOBean;
	
	@EJB
	private ErrorMessageService errorMessageService;

	public ArrayList<String> validateUserForRegistration(int birthYear, int birthMonth, int birthDay, UserModel userToValidate, ArrayList<String> validationErrorMessages) {
		
		isBasicFormatCorrect(userToValidate, validationErrorMessages);
		
		isBirthdateCorrect(birthYear, birthMonth, birthDay, validationErrorMessages);
		
		if(userToValidate.getGender() == null){
			errorMessageService.setValidationErrorMessage("gender", validationErrorMessages);
		}
		if(!isUsernameUnique(userToValidate.getUsername())){
			errorMessageService.setValidationErrorMessage("username", validationErrorMessages);
		}
		if(userToValidate.getUsername() == null || userToValidate.getUsername().trim().isEmpty()) {
			errorMessageService.setValidationErrorMessage("username", validationErrorMessages);
		}
		
		if(userToValidate.getCountry() == null){
			errorMessageService.setValidationErrorMessage("country", validationErrorMessages);
		}

		if (!isEmailFormatCorrect(userToValidate.getEmail())) {
			errorMessageService.setValidationErrorMessage("email", validationErrorMessages);
		} 
		if(!isEmailUnique(userToValidate.getEmail())){
			errorMessageService.setValidationErrorMessage("email", validationErrorMessages);
		}
		if(!isPasswordCorrect(userToValidate.getVisiblePassword())){
			errorMessageService.setValidationErrorMessage("password", validationErrorMessages);
		}
		
		return errorMessageService.getValidationErrorMessages();

	}

	private ArrayList<String> isBasicFormatCorrect(UserModel userToValidate, ArrayList<String> validationErrorMessages) {

		if (!isContainingOnlyLetters(userToValidate.getFirstName())) {
			errorMessageService.setValidationErrorMessage("firstName", validationErrorMessages);
		}

		if (!isContainingOnlyLetters(userToValidate.getLastName())) {
			errorMessageService.setValidationErrorMessage("lastName", validationErrorMessages);
		}

		if (!isContainingOnlyLetters(userToValidate.getCity())) {
			errorMessageService.setValidationErrorMessage("city", validationErrorMessages);
		}

		return errorMessageService.getValidationErrorMessages();
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
	
	private boolean isPasswordCorrect(String password){
		if(password == null || password.trim().isEmpty()){
			
			return false;
		}
		return true;
	}

	private ArrayList<String> isBirthdateCorrect(int birthYear, int birthMonth, int birthDay, ArrayList<String> validationErrorMessages) {
		System.out.println("day " + birthDay);
		System.out.println("month " + birthMonth);
		System.out.println("year " + birthYear);
		if(birthDay == 0){
			errorMessageService.setValidationErrorMessage("birthday", validationErrorMessages);
		}
		if(birthMonth == 0){
			errorMessageService.setValidationErrorMessage("birthmonth", validationErrorMessages);
		}
		if(birthYear == 0){
			errorMessageService.setValidationErrorMessage("birthyear", validationErrorMessages);
		}
		return validationErrorMessages;
	}


}
