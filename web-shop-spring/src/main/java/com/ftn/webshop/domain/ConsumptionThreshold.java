package com.ftn.webshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ConsumptionThreshold extends BaseEntity{

    private Double priceFrom;

    private Double priceTo;

    private Double percentageAward;

    @JsonIgnore
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

    public Double getPercentageAward() {
        return percentageAward;
    }

    public void setPercentageAward(Double percentageAward) {
        this.percentageAward = percentageAward;
    }

    public CustomerCategory getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(CustomerCategory customerCategory) {
        this.customerCategory = customerCategory;
    }

    @Override
    public String toString() {
        return "ConsumptionThreshold{" +
                "priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", percentageAward=" + percentageAward +
                ", customerCategory=" + customerCategory.getName() +
                '}';
    }
}
