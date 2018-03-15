package com.quotescoins.util.exception.data;

/**
 * 
 * @author <a href="mailto:me@jeffersonortiz.com">Jefferson Ortiz Quiroga</a>
 * @version 1.0
 */
public class NoDataAccessNameException extends DataAccessException {
	/**
	 * Serializer version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default contructor
	 * @param String message
	 */
	public NoDataAccessNameException(String message) {
		super(message);
	}
}
