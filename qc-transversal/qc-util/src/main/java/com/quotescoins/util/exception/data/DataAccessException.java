package com.quotescoins.util.exception.data;

/**
 * 
 * @author <a href="mailto:me@jeffersonortiz.com">Jefferson Ortiz Quiroga</a>
 * @version 1.0
 */
public class DataAccessException extends Exception {

	/**
	 * Serializer version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default construct
	 * @param String message
	 */
	public DataAccessException(String message) {
		super(message);
	}
	
	/**
	 * Cause constructor
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
