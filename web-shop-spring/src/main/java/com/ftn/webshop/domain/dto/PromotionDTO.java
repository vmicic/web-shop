package com.ftn.webshop.domain.dto;

public class PromotionDTO {

    private String name;

    private Double percentageDiscount;

    public PromotionDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }
}
