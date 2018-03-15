package com.quotescoins.core;

import com.quotescoins.dto.ExchangeDto;

public interface BusinessFacade {

    public ExchangeDto getBase(String base);

    public ExchangeDto getBaseByRate(String base, String rate);
}
