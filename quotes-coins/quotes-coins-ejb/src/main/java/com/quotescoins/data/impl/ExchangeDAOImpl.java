package com.quotescoins.data.impl;

import com.mongodb.MongoQueryException;
import com.quotescoins.data.ExchangeDAO;
import com.quotescoins.model.Exchange;
import com.quotescoins.util.data.dao.GenericDaoImpl;
import com.quotescoins.util.exception.data.DataAccessError;
import com.quotescoins.util.exception.data.DataAccessException;

import javax.persistence.*;
import java.util.logging.Logger;

public class ExchangeDAOImpl extends GenericDaoImpl<Exchange, String> implements ExchangeDAO {

    /**
     * Default attribute that print messages in server console
     */
    private final static Logger logger = Logger.getLogger(ExchangeDAOImpl.class.getName());

    /**
     * Attribute to save instance same class
     */
    public static ExchangeDAO instance;

    /**
     * Method that implement Singleton pattern
     *
     * @param EntityManager em
     * @return ProcessDao
     */
    public static ExchangeDAO getInstance(EntityManager em){
        if(instance == null) {
            instance = new ExchangeDAOImpl(em);
        }
        return instance;
    }

    /**
     * Default constructor
     * @param EntityManager em
     */
    public ExchangeDAOImpl(EntityManager em){
        super(Exchange.class, em);
    }

    /**
     * @see ExchangeDAO#getExchageByBase()
     */
    public Exchange getExchageByBase(String base) throws DataAccessException {
        try {
            // 1. set query
            String mongoQL = "{ $and: [ { base : '" + base + "' } ] }";
            Query queryHQL = getEntityManager().createNativeQuery(mongoQL, Exchange.class);
            // 2. execute query and set results
            Exchange result = (Exchange) queryHQL.getSingleResult();
            // 3. lazily initialize
            result.setLazyHibernateSetup();
            // 4. return data
            return result;
        } catch (CloneNotSupportedException e) {
            DataAccessError error = DataAccessError.NO_CLONABLE_ENTITY;
            logger.info(error.message() + ": " + e.getMessage());
            throw new DataAccessException(error.message(), e.getCause());
        } catch (MongoQueryException e) {
            DataAccessError error = DataAccessError.QUERY_ERROR;
            logger.info(error.message() + ": " + e.getErrorMessage());
            throw new DataAccessException(error.message(), e.getCause());
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            DataAccessError error = DataAccessError.GENERAL;
            logger.info(error.message() + ": " + e.getMessage());
            throw new DataAccessException(error.message(), e.getCause());
        }
    }
}
