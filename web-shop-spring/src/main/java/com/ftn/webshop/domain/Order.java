package com.ftn.webshop.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Role(Role.Type.EVENT)
@Timestamp("date")
public class Order extends BaseEntity {

    private String code;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime localDateTime;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @Enumerated(EnumType.STRING)
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

    public String getOrderId() {
        return code;
    }

    public void setOrderId(String orderId) {
        this.code = orderId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.getId() +
                ", date=" + date +
                ", user=" + user.getUsername() +
                ", orderLines=" + orderLines +
                ", orderState=" + orderState +
                ", discounts=" + discounts +
                ", priceBeforeDiscount=" + priceBeforeDiscount +
                ", priceAfterDiscount=" + priceAfterDiscount +
                ", bonusPointsSpent=" + bonusPointsSpent +
                ", bonusPointsAwarded=" + bonusPointsAwarded +
                '}';
    }
}
