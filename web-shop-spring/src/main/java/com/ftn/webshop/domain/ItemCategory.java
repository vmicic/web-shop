package com.ftn.webshop.domain;

import javax.persistence.Entity;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCategory that = (ItemCategory) o;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}
