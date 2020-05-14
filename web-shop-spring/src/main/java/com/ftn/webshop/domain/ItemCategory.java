package com.ftn.webshop.domain;

import javax.persistence.Entity;

@Entity
public class ItemCategory extends BaseEntity {

    private String code;

    private String name;

    public ItemCategory() {
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
}
