package com.ftn.webshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.kie.api.definition.type.Duration;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Role(Role.Type.EVENT)
@Timestamp("startTime")
@Duration("duration")
public class Promotion extends BaseEntity {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @JsonIgnore
    private Long duration;

    private Double percentageDiscount;

    @JsonIgnore
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "promotion_item_category",
               joinColumns =@JoinColumn(name = "promotion_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "item_category_id", referencedColumnName = "id"))
    private List<ItemCategory> itemCategories = new ArrayList<>();

    public Promotion() {
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    public void setItemCategories(List<ItemCategory> itemCategories) {
        this.itemCategories = itemCategories;
    }

    public void addItemCategory(ItemCategory itemCategory) {
        this.itemCategories.add(itemCategory);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", percentageDiscount=" + percentageDiscount +
                '}';
    }
}
