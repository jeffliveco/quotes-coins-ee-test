package com.quotescoins.data;

import com.quotescoins.model.Exchange;
import com.quotescoins.model.ExchangeRate;
import com.quotescoins.util.data.dao.GenericDao;
import com.quotescoins.util.exception.data.DataAccessException;

public interface ExchangeRateDAO extends GenericDao<ExchangeRate, String> {

    public ExchangeRate getRateByExchange(String base, Exchange exchange) throws DataAccessException;
}
