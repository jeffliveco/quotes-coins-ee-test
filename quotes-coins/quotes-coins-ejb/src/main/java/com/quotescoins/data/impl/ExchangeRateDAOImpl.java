package com.quotescoins.data.impl;

import com.mongodb.MongoQueryException;
import com.quotescoins.data.ExchangeRateDAO;
import com.quotescoins.model.Exchange;
import com.quotescoins.model.ExchangeRate;
import com.quotescoins.util.data.dao.GenericDaoImpl;
import com.quotescoins.util.exception.data.DataAccessError;
import com.quotescoins.util.exception.data.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.logging.Logger;

public class ExchangeRateDAOImpl extends GenericDaoImpl<ExchangeRate, String> implements ExchangeRateDAO {

    /**
     * Default attribute that print messages in server console
     */
    private final static Logger logger = Logger.getLogger(ExchangeRateDAOImpl.class.getName());

    /**
     * Attribute to save instance same class
     */
    public static ExchangeRateDAO instance;

    /**
     * Method that implement Singleton pattern
     *
     * @param EntityManager em
     * @return ProcessDao
     */
    public static ExchangeRateDAO getInstance(EntityManager em){
        if(instance == null) {
            instance = new ExchangeRateDAOImpl(em);
        }
        return instance;
    }

    /**
     * Default constructor
     * @param EntityManager em
     */
    public ExchangeRateDAOImpl(EntityManager em){
        super(ExchangeRate.class, em);
    }

    public ExchangeRate getRateByExchange(String base, Exchange exchange) throws DataAccessException {
        try {
            // 1. set query
            String mongoQL = "{ $and: [ { base: '" + base + "' }, { exchage: '" + exchange.getId() + "' } ] }";
            Query queryHQL = getEntityManager().createNativeQuery(mongoQL, ExchangeRate.class);
            // 2. execute query and set results
            ExchangeRate result = (ExchangeRate) queryHQL.getSingleResult();
            // 3. lazily initialize
            result.setLazyHibernateSetup();
            // 4. return data'
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
