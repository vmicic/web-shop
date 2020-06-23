package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.domain.dto.PromotionCreateDTO;
import com.ftn.webshop.repositories.PromotionRepository;
import com.ftn.webshop.services.ItemCategoryService;
import com.ftn.webshop.services.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {


    private static final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PromotionRepository promotionRepository;
    private final ItemCategoryService itemCategoryService;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ItemCategoryService itemCategoryService) {
        this.promotionRepository = promotionRepository;
        this.itemCategoryService = itemCategoryService;
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

    @Override
    public Promotion createPromotion(PromotionCreateDTO promotionDTO) {
        Promotion promotion = new Promotion();

        promotion.setName(promotionDTO.getName());
        promotion.setStartTime(promotionDTO.getStartTime());
        promotion.setEndTime(promotionDTO.getEndTime());
        promotion.setPercentageDiscount(promotionDTO.getPercentageDiscount());

        System.out.println("Duration is: " + (promotionDTO.getEndTime().getTime() - promotionDTO.getStartTime().getTime()));

        promotion.setDuration(promotionDTO.getEndTime().getTime() - promotionDTO.getStartTime().getTime());

        for(Long id : promotionDTO.getCategoryIds()) {
            ItemCategory itemCategory = this.itemCategoryService.findById(id);

            promotion.addItemCategory(itemCategory);
        }


        return this.promotionRepository.save(promotion);
    }
}
