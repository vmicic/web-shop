package com.ftn.webshop.services;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.domain.Promotion;

public interface PromotionService {

    Promotion findById(Long id);

    Promotion findPromotionForCategory(ItemCategory itemCategory);
}
