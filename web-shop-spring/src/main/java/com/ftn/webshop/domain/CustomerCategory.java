package com.ftn.webshop.domain;

import javax.persistence.Entity;

@Entity
public class CustomerCategory extends BaseEntity {

    private String categoryCode;

    private String name;

    public CustomerCategory() {
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
