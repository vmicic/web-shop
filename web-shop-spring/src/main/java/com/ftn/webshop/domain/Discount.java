package com.ftn.webshop.domain;

import javax.persistence.*;

@Entity
public class Discount extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private Double discountPercentage;

    @Enumerated(EnumType.STRING)
    private TypeOfDiscountForItem typeOfDiscountForItem;

    public Discount() {
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
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
}
