package com.quotescoins.util.exception.business;

/**
 * @author <a href="mailto:me@jeffersonortiz.com">Jefferson Ortiz Quiroga</a>
 * @version 1.0
 */
public enum BusinessError {
	GENERAL ("GENERAL_BUSSINESS_ERROR"),
	INVALID_ID_ATTRIBUTE ("INVALID_ID_ATTRIBUTE"),
	ATTRIBUTE_REQUIRED ("ATTRIBUTE_REQUIRED"),
	DATA_NO_EXIST ("DATA_NO_EXIST");
	
	private final String message;
	
	BusinessError(String message) {
		this.message = message;
	}
	
	public String message() { return message; }
}
