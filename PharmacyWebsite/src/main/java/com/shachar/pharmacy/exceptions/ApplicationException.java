package com.shachar.pharmacy.exceptions;

import com.shachar.pharmacy.enums.ErrorType;

public class ApplicationException extends Exception {
	private   ErrorType errorTypes;
	private String message;
	private Exception e;
	
	
	public ApplicationException(ErrorType errorTypes, String message) {
		super(message);
		this.errorTypes = errorTypes;
		this.message = message;
	}


	public ApplicationException(ErrorType errorTypes, String message, Exception e) {
		super(message);
		this.errorTypes = errorTypes;
		this.message = message;
		this.e = e;
	}


	public  ErrorType getErrorTypes() {
		return errorTypes;
	}




	
}
