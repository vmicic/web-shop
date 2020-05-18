package com.ftn.webshop.domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class ItemCategory extends BaseEntity {
    private String name;

    private Double maxPercentageDiscount;

    public ItemCategory() {
    }

    public Double getMaxPercentageDiscount() {
        return maxPercentageDiscount;
    }

    public void setMaxPercentageDiscount(Double maxPercentageDiscount) {
        this.maxPercentageDiscount = maxPercentageDiscount;
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
