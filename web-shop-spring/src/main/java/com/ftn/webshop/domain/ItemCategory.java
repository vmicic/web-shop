package com.ftn.webshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class ItemCategory extends BaseEntity {
    private String name;

    private Double maxPercentageDiscount;

    @JsonIgnore
    @ManyToMany(mappedBy = "itemCategories")
    private List<Promotion> promotions;

    public ItemCategory() {
    }

    public Double getMaxPercentageDiscount() {
        return maxPercentageDiscount;
    }

    public void setMaxPercentageDiscount(Double maxPercentageDiscount) {
        this.maxPercentageDiscount = maxPercentageDiscount;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCategory that = (ItemCategory) o;
        return this.getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "name='" + name + '\'' +
                ", maxPercentageDiscount=" + maxPercentageDiscount +
                '}';
    }
}
