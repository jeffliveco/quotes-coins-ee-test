package com.quotescoins.core;

import com.quotescoins.dto.ExchangeDto;
import com.quotescoins.dto.RateDto;

public interface BusinessFacade {

    public ExchangeDto getBase(String base);

    public ExchangeDto getBaseByRate(String base, String rate);

    public RateDto getValueByBaseAndRate(String base, String rate, Float value);
}
