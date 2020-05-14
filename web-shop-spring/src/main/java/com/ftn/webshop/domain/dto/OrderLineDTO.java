package com.ftn.webshop.domain.dto;

public class OrderLineDTO {

    private String itemCode;
    private Integer quantity;

    public OrderLineDTO() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
                "code='" + itemCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
