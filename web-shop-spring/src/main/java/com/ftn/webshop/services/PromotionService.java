package com.ftn.webshop.services;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.domain.dto.PromotionCreateDTO;

import java.util.List;

public interface PromotionService {

    Promotion findById(Long id);

    Promotion findPromotionForCategory(ItemCategory itemCategory);

    List<Promotion> findAll();

    Promotion createPromotion(PromotionCreateDTO promotionDTO);
}
