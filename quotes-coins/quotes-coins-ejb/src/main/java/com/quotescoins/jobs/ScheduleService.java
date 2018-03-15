package com.quotescoins.jobs;

import com.quotescoins.data.ExchangeDAO;
import com.quotescoins.data.ExchangeRateDAO;
import com.quotescoins.data.impl.ExchangeDAOImpl;
import com.quotescoins.data.impl.ExchangeRateDAOImpl;
import com.quotescoins.model.Exchange;
import com.quotescoins.model.ExchangeRate;
import com.quotescoins.model.Rate;
import com.quotescoins.util.exception.business.BusinessCoreException;
import com.quotescoins.util.exception.data.DataAccessException;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Singleton
@Startup
public class ScheduleService {

    /**
     * Default attribute that print messages in server console
     */
    private final static Logger logger = Logger.getLogger(ScheduleService.class.getName());

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
    public ScheduleService(){}

    @PostConstruct
    public void init(){
        logger.info("Init ScheduleService");
        exchangeService = ExchangeDAOImpl.getInstance(this.em);
        exchangeRateService = ExchangeRateDAOImpl.getInstance(this.em);
    }

    @Lock(LockType.READ)
    @Schedule(second = "*", minute = "*/10", hour = "*", persistent = false)
    public void updateExchageSchedule() throws InterruptedException {
        try {
            logger.info("run schedule");

            Exchange base = exchangeService.getExchageByBase("USD");
            if(base != null){
                List<ExchangeRate> exchangesData = new ArrayList<>();
                for(ExchangeRate exchange : base.getExchangeRates()){
                    List<Rate> ratesData = new ArrayList<>();
                    for(Rate rate : exchange.getRates()){
                        rate.setActive(false);
                        ratesData.add(rate);
                    }

                    Rate newRate = new Rate();
                    if(ratesData.size() > 0) {
                        Double randomGenerator = Math.random();
                        if (randomGenerator > .5) {
                            newRate.setValue(ratesData.get(ratesData.size() - 1).getValue() + 0.0005f);
                        } else {
                            newRate.setValue(ratesData.get(ratesData.size() - 1).getValue() - 0.0005f);
                        }
                        newRate.setActive(true);
                        newRate.setCreatedAt(new Date());
                    } else {
                        newRate.setActive(true);
                        newRate.setCreatedAt(new Date());
                        newRate.setValue(new Random().nextFloat());
                    }

                    ratesData.add(newRate);

                    exchange.setRates(ratesData);
                    exchangesData.add(exchange);
                }

                base.setExchangeRates(exchangesData);

                exchangeService.update(base);
            }

        } catch (DataAccessException e){
            throw new InterruptedException(e.getMessage());
        }
    }
}
