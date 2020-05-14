package com.ftn.webshop.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
public class Item extends BaseEntity {

    //TODO napraviti sifru

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "item_category_id", referencedColumnName = "id")
    private ItemCategory itemCategory;

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

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
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
}
