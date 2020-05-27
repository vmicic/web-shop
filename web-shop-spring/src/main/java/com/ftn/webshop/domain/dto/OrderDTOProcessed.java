package com.ftn.webshop.domain.dto;

import java.util.List;

public class OrderDTOProcessed {

    private Long orderId;

    private List<OrderLineDTO> orderLineDTOs;

    private List<DiscountDTO> discountDTOs;

    private Double priceBeforeAnyDiscount;

    private Double priceAfterOrderLineDiscounts;

    private Double percentageDiscount;

    private Double priceAfterAllDiscounts;

    private Double awardPointsSpent;

    private Integer awardPoints;

    private Integer awardPointsEarned;

    public OrderDTOProcessed() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderLineDTO> getOrderLineDTOs() {
        return orderLineDTOs;
    }

    public void setOrderLineDTOs(List<OrderLineDTO> orderLineDTOs) {
        this.orderLineDTOs = orderLineDTOs;
    }

    public List<DiscountDTO> getDiscountDTOs() {
        return discountDTOs;
    }

    public void setDiscountDTOs(List<DiscountDTO> discountDTOs) {
        this.discountDTOs = discountDTOs;
    }

    public Double getPriceBeforeAnyDiscount() {
        return priceBeforeAnyDiscount;
    }

    public void setPriceBeforeAnyDiscount(Double priceBeforeAnyDiscount) {
        this.priceBeforeAnyDiscount = priceBeforeAnyDiscount;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public Double getPriceAfterAllDiscounts() {
        return priceAfterAllDiscounts;
    }

    public void setPriceAfterAllDiscounts(Double priceAfterAllDiscounts) {
        this.priceAfterAllDiscounts = priceAfterAllDiscounts;
    }

    public Double getAwardPointsSpent() {
        return awardPointsSpent;
    }

    public void setAwardPointsSpent(Double awardPointsSpent) {
        this.awardPointsSpent = awardPointsSpent;
    }

    public Double getPriceAfterOrderLineDiscounts() {
        return priceAfterOrderLineDiscounts;
    }

    public void setPriceAfterOrderLineDiscounts(Double priceAfterOrderLineDiscounts) {
        this.priceAfterOrderLineDiscounts = priceAfterOrderLineDiscounts;
    }

    public Integer getAwardPoints() {
        return awardPoints;
    }

    public void setAwardPoints(Integer awardPoints) {
        this.awardPoints = awardPoints;
    }

    public Integer getAwardPointsEarned() {
        return awardPointsEarned;
    }

    public void setAwardPointsEarned(Integer awardPointsEarned) {
        this.awardPointsEarned = awardPointsEarned;
    }
}
