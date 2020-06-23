package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.repositories.ItemCategoryRepository;
import com.ftn.webshop.services.ItemCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryServiceImpl(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @Override
    public List<ItemCategory> getAll() {
        return this.itemCategoryRepository.findAll();
    }

    @Override
    public ItemCategory findById(Long id) {
        return this.itemCategoryRepository.findById(id).orElse(null);
    }
}
