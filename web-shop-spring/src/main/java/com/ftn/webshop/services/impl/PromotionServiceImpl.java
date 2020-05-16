package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.repositories.PromotionRepository;
import com.ftn.webshop.services.PromotionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion findById(Long id) {
        Optional<Promotion> promotion = this.promotionRepository.findById(id);

        return promotion.orElse(null);
    }
}
