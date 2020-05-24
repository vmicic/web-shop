package com.ftn.webshop.domain.dto;

import com.ftn.webshop.domain.CustomerCategory;

public class ItemDTOBuyer {

    private Long id;

    private String name;

    private Double price;

    private ItemCategoryDTO itemCategory;

    private PromotionDTO promotion;

    public ItemDTOBuyer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategoryDTO getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategoryDTO itemCategory) {
        this.itemCategory = itemCategory;
    }

    public PromotionDTO getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionDTO promotion) {
        this.promotion = promotion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
