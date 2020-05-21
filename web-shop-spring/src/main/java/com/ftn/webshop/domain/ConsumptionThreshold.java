package com.ftn.webshop.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ConsumptionThreshold extends BaseEntity{

    private Double priceFrom;

    private Double priceTo;

    private Double percentageDiscount;

    @ManyToOne
    @JoinColumn(name = "customer_category_id", referencedColumnName = "id")
    private CustomerCategory customerCategory;

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

    @Override
    public String toString() {
        return "ConsumptionThreshold{" +
                "priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", percentageDiscount=" + percentageDiscount +
                ", customerCategory=" + customerCategory.getName() +
                '}';
    }
}
