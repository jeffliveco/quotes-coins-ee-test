package com.quotescoins.model;

import com.quotescoins.dto.ExchangeRateDto;
import com.quotescoins.dto.RateDto;
import com.quotescoins.util.model.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate implements AbstractEntity {

    /**
     *  Serialize Data
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String base;
    @ManyToOne
    private Exchange exchage;
    @ElementCollection
    private List<Rate> rates = new ArrayList<>();

    /**
     * Default constructor
     */
    public ExchangeRate() { }

    /**
     * DTO constructor
     * @param dto
     */
    public ExchangeRate(ExchangeRateDto dto) {
        this.id = dto.getId();
        this.base = dto.getBase();
        for(RateDto subEntity : dto.getRates()){
            this.rates.add(new Rate(subEntity));
        }
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id='" + id + '\'' +
                ", base='" + base + '\'' +
                ", exchage=" + exchage.toString() +
                ", rates=" + rates.size() +
                '}';
    }

    @Override
    public void setLazyHibernateSetup() throws CloneNotSupportedException {
        this.setExchage(null);
    }

    @Override
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

    public Exchange getExchage() {
        return exchage;
    }

    public void setExchage(Exchange exchage) {
        this.exchage = exchage;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}