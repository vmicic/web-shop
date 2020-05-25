package com.ftn.webshop.services;

import com.ftn.webshop.domain.Discount;

import java.util.List;

public interface DiscountService {

    void createDiscount(Discount discount);

    List<Discount> findAllByOrderId(Long id);
}
