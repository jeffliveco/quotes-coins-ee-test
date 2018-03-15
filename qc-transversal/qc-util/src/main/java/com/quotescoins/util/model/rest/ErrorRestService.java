package com.quotescoins.util.model.rest;

public enum ErrorRestService {
	NO_API_KEY("NO_API_KEY");
	
	private final String message;
	
	ErrorRestService (String message) {
		this.message = message;
	}
	
	public String message() { return message; }
}
