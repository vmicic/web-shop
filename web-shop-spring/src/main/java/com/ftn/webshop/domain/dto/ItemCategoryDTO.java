package com.ftn.webshop.domain.dto;

public class ItemCategoryDTO {

    private String name;

    private Double maxPercentageDiscount;

    public ItemCategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxPercentageDiscount() {
        return maxPercentageDiscount;
    }

    public void setMaxPercentageDiscount(Double maxPercentageDiscount) {
        this.maxPercentageDiscount = maxPercentageDiscount;
    }
}
