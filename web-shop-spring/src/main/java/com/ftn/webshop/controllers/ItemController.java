package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Item;
import com.ftn.webshop.domain.dto.ItemDTOBuyer;
import com.ftn.webshop.services.ItemService;
import com.ftn.webshop.services.impl.PromotionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {

    private static Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    public ResponseEntity<?> getAllItems() {
        List<ItemDTOBuyer> items = this.itemService.getAllItemsForSale();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
