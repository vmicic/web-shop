package com.ftn.webshop.services;

import com.ftn.webshop.domain.Discount;
import com.ftn.webshop.domain.DiscountForItem;

import java.util.List;

public interface DiscountForItemService {

    void createDiscountForItem(DiscountForItem discountForItem);

    List<DiscountForItem> findAllByOrderLineId(Long id);
}
