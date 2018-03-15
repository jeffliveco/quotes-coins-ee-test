package com.quotescoins.util.exception.data;

/**
 * 
 * @author jeffersonortiz
 *
 */
public class NoDataAccessExistException extends DataAccessException {
	/**
	 * Serializer version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 * @param String message
	 */
	public NoDataAccessExistException (String message) {
		super(message);
	}

}
