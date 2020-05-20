package com.ftn.webshop.domain;

import javax.persistence.*;

@Entity
public class Discount extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private Double discountPercentage;

    @Enumerated(EnumType.STRING)
    private TypeOfDiscount typeOfDiscount;

    public Discount(Order order, Double discountPercentage, TypeOfDiscount typeOfDiscount) {
        this.order = order;
        this.discountPercentage = discountPercentage;
        this.typeOfDiscount = typeOfDiscount;
    }

    public Discount() {
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public TypeOfDiscount getTypeOfDiscountForItem() {
        return typeOfDiscount;
    }

    public void setTypeOfDiscountForItem(TypeOfDiscount typeOfDiscount) {
        this.typeOfDiscount = typeOfDiscount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "order=" + order.getId() +
                ", discountPercentage=" + discountPercentage +
                ", typeOfDiscount=" + typeOfDiscount +
                '}';
    }
}
