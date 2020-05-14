package com.ftn.webshop.domain.dto;

import javax.persistence.criteria.CriteriaBuilder;

public class OrderLineDTO {

    private String code;
    private Integer quantity;

    public OrderLineDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLineDTO{" +
                "code='" + code + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
