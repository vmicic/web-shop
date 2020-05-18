package com.ftn.webshop.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderLine extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "orderLine")
    private List<DiscountForItem> discountsForItem = new ArrayList<>();

    private Integer serialNumber;

    private Double pricePerUnit;

    private Double priceTotal;

    private Double percentageDiscount;

    private Double priceTotalFinal;

    //TODO
    //lista primenjenih popusta na stavku

    public OrderLine() {
    }

    public List<DiscountForItem> getDiscountsForItem() {
        return discountsForItem;
    }

    public void setDiscountsForItem(List<DiscountForItem> discountsForItem) {
        this.discountsForItem = discountsForItem;
    }

    public void addDiscountForItem(DiscountForItem discountForItem) {
        this.discountsForItem.add(discountForItem);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
        this.priceTotalFinal = (1 - this.percentageDiscount/100) * this.priceTotal;
    }

    public Double getPriceTotalFinal() {
        return priceTotalFinal;
    }

    public void setPriceTotalFinal(Double priceTotalFinal) {
        this.priceTotalFinal = priceTotalFinal;
    }


    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + this.getId() +
                ", item=" + item.getName() +
                ", quantity=" + quantity +
                ", discountsForItem=" + discountsForItem +
                ", serialNumber=" + serialNumber +
                ", pricePerUnit=" + pricePerUnit +
                ", priceTotal=" + priceTotal +
                ", percentageDiscount=" + percentageDiscount +
                ", priceTotalFinal=" + priceTotalFinal +
                '}';
    }
}
