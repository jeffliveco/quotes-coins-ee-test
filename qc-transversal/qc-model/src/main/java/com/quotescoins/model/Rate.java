package com.quotescoins.model;

import com.quotescoins.dto.RateDto;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Rate {

    private Float value;
    private Date createdAt;
    private Boolean active;

    /**
     * Default constructor
     */
    public Rate() { }

    /**
     * DTO constructor
     * @param dto
     */
    public Rate(RateDto dto) {
        this.value = dto.getValue();
        this.createdAt = dto.getCreatedAt();
        this.active = dto.getActive();
    }

    @Override
    public String toString() {
        return "Rate{" +
                "value=" + value +
                ", createdAt=" + createdAt +
                ", active=" + active +
                '}';
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
