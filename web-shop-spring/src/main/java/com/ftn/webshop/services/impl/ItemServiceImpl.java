package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Item;
import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.domain.dto.ItemCategoryDTO;
import com.ftn.webshop.domain.dto.ItemDTOBuyer;
import com.ftn.webshop.domain.dto.PromotionDTO;
import com.ftn.webshop.repositories.ItemRepository;
import com.ftn.webshop.services.ItemService;
import com.ftn.webshop.services.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private static Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepository;
    private final PromotionService promotionService;

    public ItemServiceImpl(ItemRepository itemRepository, PromotionService promotionService) {
        this.itemRepository = itemRepository;
        this.promotionService = promotionService;
    }

    @Override
    public Item findItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        return item.orElse(null);
    }

    @Override
    public List<ItemDTOBuyer> getAllItemsForSale() {
        List<Item> items = this.itemRepository.findAll();

        List<ItemDTOBuyer> itemsDTO = new ArrayList<>();

        for(Item item: items) {
            ItemDTOBuyer itemDTO = new ItemDTOBuyer();

            itemDTO.setName(item.getName());
            itemDTO.setId(item.getId());
            itemDTO.setPrice(item.getPrice());

            ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
            itemCategoryDTO.setName(item.getCategory().getName());

            itemDTO.setItemCategory(itemCategoryDTO);

            Promotion promotion = promotionService.findPromotionForCategory(item.getCategory());
            if(promotion != null) {
                PromotionDTO promotionDTO = new PromotionDTO();
                promotionDTO.setName(promotion.getName());
                promotionDTO.setPercentageDiscount(promotion.getPercentageDiscount());
                itemDTO.setPromotion(promotionDTO);
            }

            itemsDTO.add(itemDTO);
        }

        return itemsDTO;
    }

    @Override
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public void saveAll(List<Item> items) {
        this.itemRepository.saveAll(items);
    }
}
