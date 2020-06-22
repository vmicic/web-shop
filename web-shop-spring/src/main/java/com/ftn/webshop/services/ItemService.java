package com.ftn.webshop.services;

import com.ftn.webshop.domain.Item;
import com.ftn.webshop.domain.dto.ItemDTOBuyer;

import java.util.List;

public interface ItemService {

    Item findItemById(Long id);

    List<ItemDTOBuyer> getAllItemsForSale();

    List<Item> findAll();

    void saveAll(List<Item> items);

    void addAmount(Long id, Integer amount);
}
