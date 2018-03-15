package com.quotescoins.dto;

import com.quotescoins.model.Rate;

import java.util.Date;

public class RateDto {

    private Float value;
    private Date createdAt;
    private Boolean active;

    /**
     * Default constructor
     */
    public RateDto() { }

    /**
     * Entity constructor
     * @param entity
     */
    public RateDto(Rate entity) {
        this.value = entity.getValue();
        this.createdAt = entity.getCreatedAt();
        this.active = entity.getActive();
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
