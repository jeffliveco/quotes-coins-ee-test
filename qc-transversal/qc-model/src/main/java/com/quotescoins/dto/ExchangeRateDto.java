package com.quotescoins.dto;

import com.quotescoins.model.ExchangeRate;
import com.quotescoins.model.Rate;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateDto {
	private String id;
	private String base;
	private List<RateDto> rates = new ArrayList<>();

	/**
	 * Default constructor
	 */
	public ExchangeRateDto() { }


	/**
	 * Entity constructor
	 * @param entity
	 */
	public ExchangeRateDto(ExchangeRate entity) {
		this.id = entity.getId();
		this.base = entity.getBase();
		for(Rate subEntity : entity.getRates()) {
			this.rates.add(new RateDto(subEntity));
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public List<RateDto> getRates() {
		return rates;
	}

	public void setRates(List<RateDto> rates) {
		this.rates = rates;
	}
}
