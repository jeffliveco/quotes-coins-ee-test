package com.quotescoins.data;

import com.quotescoins.model.Exchange;
import com.quotescoins.util.data.dao.GenericDao;
import com.quotescoins.util.exception.data.DataAccessException;

public interface ExchangeDAO extends GenericDao<Exchange, String> {

    public Exchange getExchageByBase(String base) throws DataAccessException;
}
