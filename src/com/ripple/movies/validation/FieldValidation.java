package com.ripple.movies.validation;
import com.ripple.movies.exception.InvalidInputException;
import com.movies.controller.MoviesList;

public class FieldValidation {
	public static boolean isValidationString(String input) {
		 return input != null && input.matches("^[a-zA-Z\\s]+$");
	}
	public static boolean isValidationInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch(NumberFormatException e) {
			throw new InvalidInputException("Invalid Input for integer");
			
		}
	}
	
	
	

}
