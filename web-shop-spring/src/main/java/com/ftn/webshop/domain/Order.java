package com.ftn.webshop.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private Long orderId;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    private OrderState orderState;

    @OneToMany(mappedBy = "order")
    private List<Discount> discounts;

    private Double priceBeforeDiscount;
    private Double priceAfterDiscount;
    private Double bonusPointsSpent;
    private Double bonusPointsAwarded;

    public Order() {
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }


    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Double getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(Double priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public Double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(Double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }


    public Double getBonusPointsSpent() {
        return bonusPointsSpent;
    }

    public void setBonusPointsSpent(Double bonusPointsSpent) {
        this.bonusPointsSpent = bonusPointsSpent;
    }

    public Double getBonusPointsAwarded() {
        return bonusPointsAwarded;
    }

    public void setBonusPointsAwarded(Double bonusPointsAwarded) {
        this.bonusPointsAwarded = bonusPointsAwarded;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
