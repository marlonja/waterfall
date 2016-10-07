package com.waterfall.validators;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import com.waterfall.models.UserModel;
import com.waterfall.storage.UserDAOBean;

@Stateful
public class RegistrationValidator {

	private String regexOnlyLetter = "^[-A-ZÅÄÖa-zåäö]+$";
	private String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String regexOnlyNumbers = "^[0-9-]+$";

	@EJB
	private UserDAOBean userDAOBean;
	
	private ExternalContext externalContext;
	
	private Map<String, Object> validationErrorSession;

	public ArrayList<String> validateUserForRegistration(UserModel userToValidate) {
		
		isBasicFormatCorrect(userToValidate);
		
//		if(userToValidate.getGender() == null){
//			return false;
//		}
//		if(!isUsernameUnique(userToValidate.getUsername())){
//			System.out.println("Username exists vi are i validation");
//			return false;
//		}
//		
//		if(userToValidate.getCountry() == null){
//			return false;
//		}
//		
//		
//
//		if (isEmailFormatCorrect(userToValidate.getEmail()) && isEmailUnique(userToValidate.getEmail())) {
//			return true;
//		} else {
//			return false;
//		}
		
		return getValidationErrorMessages();

	}

	private ArrayList<String> isBasicFormatCorrect(UserModel userToValidate) {

		if (!isContainingOnlyLetters(userToValidate.getFirstName())) {
			setValidationErrorMessage("firstName");
		}

		if (!isContainingOnlyLetters(userToValidate.getLastName())) {
			setValidationErrorMessage("lastName");
		}

		if (!isContainingOnlyLetters(userToValidate.getCity())) {
			setValidationErrorMessage("city");
		}

		return getValidationErrorMessages();
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
	
	public ArrayList<String> setValidationErrorMessage(String validationErrorMessage) {
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		validationErrorSession = externalContext.getSessionMap();
		
		ArrayList<String> validationErrorMessages = getValidationErrorMessages();
		
//		if(validationErrorMessages == null) {
//			validationErrorMessages = new ArrayList<String>();
//		}
		
		validationErrorMessages.add(validationErrorMessage);
		
		validationErrorSession.put("validationErrorMessages", validationErrorMessages);
		return validationErrorMessages;
	} 
	
	public ArrayList<String> getValidationErrorMessages() {
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		validationErrorSession = externalContext.getSessionMap();
		
		if(validationErrorSession.get("validationErrorMessages") == null) {
			ArrayList<String> validationErrorMessages = new ArrayList<String>();
			validationErrorSession.put("validationErrorMessages", validationErrorMessages);
		}
		
		return (ArrayList<String>) validationErrorSession.get("validationErrorMessages");
	}
	

}
