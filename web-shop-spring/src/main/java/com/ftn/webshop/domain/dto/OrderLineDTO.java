package com.ftn.webshop.domain.dto;

import com.ftn.webshop.domain.DiscountForItem;

import java.util.List;

public class OrderLineDTO {

    private Integer serialNumber;
    private String itemName;
    private String itemCategoryName;
    private Double pricePerUnit;
    private Double priceAfterDiscount;
    private List<DiscountForItemDTO> discountForItemDTOs;

    private Long itemId;
    private Integer quantity;

    public OrderLineDTO() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
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

    public List<DiscountForItemDTO> getDiscountForItemDTOs() {
        return discountForItemDTOs;
    }

    public void setDiscountForItemDTOs(List<DiscountForItemDTO> discountForItemDTOs) {
        this.discountForItemDTOs = discountForItemDTOs;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(Double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    @Override
    public String toString() {
        return "OrderLineDTO{" +
                "itemCode='" + itemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
