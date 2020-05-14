package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Item;
import com.ftn.webshop.repositories.ItemRepository;
import com.ftn.webshop.services.ItemService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item findItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        return item.orElse(null);
    }

    @Override
    public Item findByCode(String code) {
        Optional<Item> item = itemRepository.findByCodeIgnoreCase(code);

        return item.orElse(null);
    }
}
