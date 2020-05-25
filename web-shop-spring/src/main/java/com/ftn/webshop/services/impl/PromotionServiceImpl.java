package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.repositories.PromotionRepository;
import com.ftn.webshop.services.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {


    private static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion findById(Long id) {
        Optional<Promotion> promotion = this.promotionRepository.findById(id);

        return promotion.orElse(null);
    }

    @Override
    public Promotion findPromotionForCategory(ItemCategory itemCategory) {
        List<Promotion> promotions = this.promotionRepository.findAll();

        for(Promotion promotion: promotions) {
            if(promotion.getStartTime().before(new Date()) && promotion.getEndTime().after(new Date())) {
                if(promotion.getItemCategories().contains(itemCategory)) {
                    return promotion;
                }
            }
        }

        return null;
    }

    @Override
    public List<Promotion> findAll() {
        return this.promotionRepository.findAll();
    }
}
