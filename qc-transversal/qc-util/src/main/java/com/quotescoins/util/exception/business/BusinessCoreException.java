package com.quotescoins.util.exception.business;

/**
 * 
 * @author <a href="mailto:me@jeffersonortiz.com">Jefferson Ortiz Quiroga</a>
 * @version 1.0
 */
public class BusinessCoreException extends RuntimeException {
	
	/**
	 * Serializer version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 * @param cause
	 */
	public BusinessCoreException(String message) {
		super(message);
	}
	
	/**
	 * Alternative constructor
	 * @param cause
	 */
	public BusinessCoreException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Alternative constructor
	 * @param String message
	 * @param Exception cause
	 */
	public BusinessCoreException(String message, Throwable cause) {
		super(message, cause);
	}
}
