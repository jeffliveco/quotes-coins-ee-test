package com.quotescoins.util.data.dao;

import java.io.Serializable;

import com.quotescoins.util.exception.data.DataAccessException;
import com.quotescoins.util.model.AbstractEntity;

/**
 * 
 * 
 * @author <a href="mailto:me@jeffersonortiz.com">Jefferson Ortiz Quiroga</a>
 * @version 1.0
 * @param <Entity>
 * @param <Id>
 */
public interface GenericDao <Entity extends AbstractEntity, Id extends Serializable> {
	
	/**
	 * Persist the newInstance object into database.
	 * 
	 * @param <Entity> newInstance
	 * @return <Id>
	 * @throws DataAccessException
	 */
	public Id create(Entity newInstance) throws DataAccessException;
 
	/**
	 * Retrieve an object that was previously persisted to the database using the indicated id as primary key.
     * 
	 * @param <Id> id
	 * @return <Entity>
	 * @throws DataAccessException
	 */
	public Entity read(Id id) throws DataAccessException;
 
	/**
	 * Save changes made to a persistent object.
	 * 
	 * @param <Entity> transientObject
	 * @throws DataAccessException
	 */
    public void update(Entity transientObject) throws DataAccessException;
 
    /**
     * Remove an object from persistent storage in the database.
     * 
     * @param <Entity> persistentObject
     * @throws DataAccessException
     */
    public void delete(Entity persistentObject) throws DataAccessException;
}
