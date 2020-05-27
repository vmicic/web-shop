package com.ftn.webshop.domain.dto;

import com.ftn.webshop.domain.TypeOfDiscount;

public class DiscountForItemDTO {

    private Long id;

    private Double discountPercentage;

    private TypeOfDiscount typeOfDiscount;

    public DiscountForItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public TypeOfDiscount getTypeOfDiscount() {
        return typeOfDiscount;
    }

    public void setTypeOfDiscount(TypeOfDiscount typeOfDiscount) {
        this.typeOfDiscount = typeOfDiscount;
    }
}
