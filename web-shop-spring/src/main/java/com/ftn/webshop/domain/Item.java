package com.ftn.webshop.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Item extends BaseEntity {

    //TODO napraviti sifru

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "item_category_id", referencedColumnName = "id")
    private ItemCategory category;

    private Double price;

    private Integer numberOnStock;

    private LocalDateTime localDateTimeCreated;

    private boolean refill;

    private boolean status;

    public Item() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOnStock() {
        return numberOnStock;
    }

    public void setNumberOnStock(Integer numberOnStock) {
        this.numberOnStock = numberOnStock;
    }

    public LocalDateTime getLocalDateTimeCreated() {
        return localDateTimeCreated;
    }

    public void setLocalDateTimeCreated(LocalDateTime localDateTimeCreated) {
        this.localDateTimeCreated = localDateTimeCreated;
    }

    public boolean isRefill() {
        return refill;
    }

    public void setRefill(boolean refill) {
        this.refill = refill;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return this.getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, category, price, numberOnStock, localDateTimeCreated, refill, status);
    }
}
