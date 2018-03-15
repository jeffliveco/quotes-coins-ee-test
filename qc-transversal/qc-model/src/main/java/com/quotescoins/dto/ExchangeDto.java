package com.quotescoins.dto;

import com.quotescoins.model.Exchange;
import com.quotescoins.model.ExchangeRate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExchangeDto {
	private String id;
	private String base;
	private Date createdAt;
	private Date updateAt;
	private List<ExchangeRateDto> exchangeRates = new ArrayList<>();

	/**
	 * Default constructor
	 */
	public ExchangeDto() {}

	/**
	 * Entity constructor
	 * @param entity
	 */
	public ExchangeDto(Exchange entity) {
		this.id = entity.getId();
		this.base = entity.getBase();
		this.createdAt = entity.getCreatedAt();
		this.updateAt = entity.getUpdateAt();
		for(ExchangeRate subEntity : entity.getExchangeRates()){
			this.exchangeRates.add(new ExchangeRateDto(subEntity));
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public List<ExchangeRateDto> getExchangeRates() {
		return exchangeRates;
	}

	public void setExchangeRates(List<ExchangeRateDto> exchangeRates) {
		this.exchangeRates = exchangeRates;
	}
}
