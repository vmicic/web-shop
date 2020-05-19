package com.ftn.webshop.domain;

import javax.persistence.*;

@Entity
public class DiscountForItem extends BaseEntity{

    private String code;

    @ManyToOne
    @JoinColumn(name = "order_line_id", referencedColumnName = "id")
    private OrderLine orderLine;

    private Double discountPercentage;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private TypeOfDiscount typeOfDiscount;

    public DiscountForItem() {
    }


    public DiscountForItem(OrderLine orderLine, Double discountPercentage, Order order, TypeOfDiscount typeOfDiscount) {
        this.orderLine = orderLine;
        this.discountPercentage = discountPercentage;
        this.order = order;
        this.typeOfDiscount = typeOfDiscount;
    }

    public TypeOfDiscount getTypeOfDiscount() {
        return typeOfDiscount;
    }

    public void setTypeOfDiscount(TypeOfDiscount typeOfDiscount) {
        this.typeOfDiscount = typeOfDiscount;
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



    @Override
    public String toString() {
        return "DiscountForItem{" +
                "id='" + this.getId() + '\'' +
                ", orderLine=" + orderLine.getId() +
                ", discountPercentage=" + discountPercentage +
                ", order=" + order.getId() +
                ", typeOfDiscountForItem=" + typeOfDiscount +
                '}';
    }
}
