package com.ftn.webshop.domain;

import javax.persistence.Entity;

@Entity
public class ConsumptionThreshold extends BaseEntity{

    private Double priceFrom;

    private Double priceTo;

    private Double percentageDiscount;

    public ConsumptionThreshold() {
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }
}
