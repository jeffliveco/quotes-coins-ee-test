package com.quotescoins.util.exception;

public class DataNoExistException extends Exception {
	
	private String dto;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public DataNoExistException(String dto, String message) {
		super(message);
		this.dto = dto;
	}

	public String getDto() {
		return dto;
	}

	public void setDto(String dto) {
		this.dto = dto;
	}
}
