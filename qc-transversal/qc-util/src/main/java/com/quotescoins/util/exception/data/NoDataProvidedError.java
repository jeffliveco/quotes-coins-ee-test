package com.quotescoins.util.exception.data;

public enum NoDataProvidedError {

	ACCOUNT_USER_EMAIL_MUST_BE_PROVIDED("ACCOUNT_USER_EMAIL_MUST_BE_PROVIDED"),
	ATTRIBUTE_REQUIRED("ATTRIBUTE_REQUIRED"),
	ACCOUNT_PASSWORD_MUST_BE_PROVIDED("ACCOUNT_PASSWORD_MUST_BE_PROVIDED");

	private final String message;

	private NoDataProvidedError(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
}
