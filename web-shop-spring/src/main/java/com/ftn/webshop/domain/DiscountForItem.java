package com.ftn.webshop.domain;

import javax.persistence.*;

@Entity
public class DiscountForItem extends BaseEntity{

    private String code;

    private OrderLine orderLine;

    private Double discountPercentage;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private TypeOfDiscountForItem typeOfDiscountForItem;

    public DiscountForItem() {
    }

    public TypeOfDiscountForItem getTypeOfDiscountForItem() {
        return typeOfDiscountForItem;
    }

    public void setTypeOfDiscountForItem(TypeOfDiscountForItem typeOfDiscountForItem) {
        this.typeOfDiscountForItem = typeOfDiscountForItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public OrderLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
