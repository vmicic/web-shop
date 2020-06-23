package com.ftn.webshop.services;

import com.ftn.webshop.domain.ItemCategory;

import java.util.List;

public interface ItemCategoryService {

    List<ItemCategory> getAll();

    ItemCategory findById(Long id);
}
