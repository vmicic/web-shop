package com.ftn.webshop.services;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.domain.dto.ItemCategoryDTO;

import java.util.List;

public interface ItemCategoryService {

    List<ItemCategory> getAll();

    ItemCategory findById(Long id);

    ItemCategory create(ItemCategoryDTO itemCategoryDTO);

    ItemCategory update(Long id, ItemCategoryDTO itemCategoryDTO);
}
