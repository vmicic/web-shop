package com.ftn.webshop.domain.dto;

public class OrderLineDTO {

    private Long itemId;
    private Integer quantity;

    public OrderLineDTO() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
                "itemCode='" + itemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
