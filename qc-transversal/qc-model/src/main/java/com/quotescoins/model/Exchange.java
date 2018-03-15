package com.quotescoins.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.quotescoins.dto.ExchangeDto;
import com.quotescoins.dto.ExchangeRateDto;
import com.quotescoins.util.model.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "exchange")
public class Exchange implements AbstractEntity {

	/**
	 *  Serialize Data
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String base;
	private Date createdAt;
	private Date updateAt;
	@OneToMany(mappedBy = "exchage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ExchangeRate> exchangeRates = new ArrayList<>();
	
	/**
	 * Default constructor
	 */
	public Exchange() { }
	
	/**
	 * DTO constructor
	 * @param dto
	 */
	public Exchange(ExchangeDto dto) {
		this.id = dto.getId();
		this.base = dto.getBase();
		this.createdAt = dto.getCreatedAt();
		this.updateAt = dto.getUpdateAt();
		for(ExchangeRateDto subEntity : dto.getExchangeRates()) {
			this.getExchangeRates().add(new ExchangeRate(subEntity));
		}
	}

	@Override
	public String toString() {
		return "Exchange{" +
				"id='" + id + '\'' +
				", base='" + base + '\'' +
				", createdAt=" + createdAt +
				", updateAt=" + updateAt +
				", rate=" + exchangeRates.size() +
				'}';
	}

	@Override
	public void setLazyHibernateSetup() throws CloneNotSupportedException {
		for(ExchangeRate subResult : this.getExchangeRates()){
			subResult.setLazyHibernateSetup();
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

	public List<ExchangeRate> getExchangeRates() {
		return exchangeRates;
	}

	public void setExchangeRates(List<ExchangeRate> exchangeRates) {
		this.exchangeRates = exchangeRates;
	}
}