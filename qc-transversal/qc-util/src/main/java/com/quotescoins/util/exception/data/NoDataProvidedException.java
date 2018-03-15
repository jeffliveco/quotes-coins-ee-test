/**
 * 
 */
package com.quotescoins.util.exception.data;

/**
 * @author dracula
 *
 */
public class NoDataProvidedException extends DataAccessException  {
	
	private static final long serialVersionUID = 1L;

	public NoDataProvidedException(String message) {
		super(message);
	}
	
	public NoDataProvidedException(String message, Throwable cause) {
		super(message, cause);
	}
}
