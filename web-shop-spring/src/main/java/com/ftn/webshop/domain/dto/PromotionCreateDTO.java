package com.ftn.webshop.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class PromotionCreateDTO {

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Belgrade")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Belgrade")
    private Date endTime;

    private Double percentageDiscount;

    private List<Long> categoryIds;

    public PromotionCreateDTO() {
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public String toString() {
        return "PromotionCreateDTO{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", percentageDiscount=" + percentageDiscount +
                ", categoryIds=" + categoryIds +
                '}';
    }
}
