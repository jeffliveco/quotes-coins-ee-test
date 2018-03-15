package com.quotescoins.util.model;

import java.io.Serializable;

/**
 * 
 * @author <a href="mailto:me@jeffersonortiz.com">Jefferson Ortiz Quiroga</a>
 * @version 1.0
 */
public interface AbstractEntity extends Serializable, Cloneable {
	
	/**
	 * Method that ensure unique attribute for database 
	 * @return String
	 */
	public String getId();
	
	/**
	 * 
	 */
	public void setLazyHibernateSetup() throws CloneNotSupportedException;
}
