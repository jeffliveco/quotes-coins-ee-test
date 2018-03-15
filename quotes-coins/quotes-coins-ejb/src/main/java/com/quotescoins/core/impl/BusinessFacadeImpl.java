package com.quotescoins.core.impl;

import com.quotescoins.core.BusinessFacadeLocal;
import com.quotescoins.core.BusinessFacadeRemote;
import com.quotescoins.data.ExchangeDAO;
import com.quotescoins.data.ExchangeRateDAO;
import com.quotescoins.data.impl.ExchangeDAOImpl;
import com.quotescoins.data.impl.ExchangeRateDAOImpl;
import com.quotescoins.dto.ExchangeDto;
import com.quotescoins.model.Exchange;
import com.quotescoins.model.ExchangeRate;
import com.quotescoins.util.exception.AttributeRequiredException;
import com.quotescoins.util.exception.business.BusinessCoreException;
import com.quotescoins.util.exception.business.BusinessError;
import com.quotescoins.util.exception.data.DataAccessException;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.logging.Logger;

@Stateless
@Local(BusinessFacadeLocal.class)
@Remote(BusinessFacadeRemote.class)
public class BusinessFacadeImpl implements BusinessFacadeLocal, BusinessFacadeRemote {

    /**
     * Default attribute that print messages in server console
     */
    private final static Logger logger = Logger.getLogger(BusinessFacadeImpl.class.getName());

    @PersistenceContext(unitName = "quotesCoins")
    private EntityManager em;

    /**
     *
     */
    private ExchangeDAO exchangeService;
    private ExchangeRateDAO exchangeRateService;

    /**
     * Default constructor
     */
    public BusinessFacadeImpl(){}

    @PostConstruct
    public void init(){
        logger.info("Init BusinessFacadeImpl");
        exchangeService = ExchangeDAOImpl.getInstance(this.em);
        exchangeRateService = ExchangeRateDAOImpl.getInstance(this.em);
    }

    /**
     * @see com.quotescoins.core.BusinessFacade#getRate
     */
    public ExchangeDto getBase(String base) {
        try {
            // 1. validate base parameter
            this.validateBaseParameter(base, "BASE");

            Exchange data = exchangeService.getExchageByBase(base);
            ExchangeDto result = null;
            if(data != null){
                result = new ExchangeDto(data);
            }

            return result;
        } catch (AttributeRequiredException e){
            throw new BusinessCoreException(e.getMessage(), e.getCause());
        } catch (DataAccessException e){
            throw new BusinessCoreException(e.getMessage(), e.getCause());
        }
    }

    public ExchangeDto getBaseByRate(String base, String rate) {
        try {
            // 1. validate base parameter
            this.validateBaseParameter(base, "BASE");

            // 2. validate rate parameter
            this.validateBaseParameter(rate, "RATE");

            Exchange data = exchangeService.getExchageByBase(base);
            ExchangeDto result = null;
            if(data != null){
                data.setExchangeRates(new ArrayList<>());

                ExchangeRate subData = exchangeRateService.getRateByExchange(rate, data);
                if(subData != null){
                    data.getExchangeRates().add(subData);
                }

                result = new ExchangeDto(data);
            }

            return result;
        } catch (AttributeRequiredException e){
            throw new BusinessCoreException(e.getMessage(), e.getCause());
        } catch (DataAccessException e){
            throw new BusinessCoreException(e.getMessage(), e.getCause());
        }
    }

    private void validateBaseParameter(String base, String atribute) throws AttributeRequiredException {
        if(base == null) {
            BusinessError error = BusinessError.ATTRIBUTE_REQUIRED;
            throw new AttributeRequiredException(error.message() + ":" + atribute.toUpperCase());
        } else if(base.isEmpty()) {
            BusinessError error = BusinessError.ATTRIBUTE_REQUIRED;
            throw new AttributeRequiredException(error.message() + ":" + atribute.toUpperCase());
        }
    }
}
