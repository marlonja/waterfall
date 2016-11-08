package com.waterfall.utils;

import java.util.ArrayList;

import javax.ejb.Stateful;

@Stateful
public class ErrorMessageService {

	public ArrayList<String> errorMessages;

	public ArrayList<String> setValidationErrorMessage(String validationErrorMessage,
			ArrayList<String> validationErrorMessages) {
		errorMessages = validationErrorMessages;
		errorMessages.add(validationErrorMessage);

		return errorMessages;
	}

	public ArrayList<String> getValidationErrorMessages() {
		if (errorMessages == null) {
			errorMessages = new ArrayList<String>();
		}

		return errorMessages;
	}

}
