package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Item;
import com.ftn.webshop.domain.dto.ItemDTOBuyer;
import com.ftn.webshop.services.ItemService;
import com.ftn.webshop.services.impl.PromotionServiceImpl;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/items")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    public ResponseEntity<?> getAllItems() {
        List<ItemDTOBuyer> items = this.itemService.getAllItemsForSale();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/refill")
    public ResponseEntity<?> getItemsForRefill() {

        List<Item> allItems = this.itemService.findAll();
        KieSession kieSession = AuthenticationController.getKieSession();

        for(Item item : allItems) {
            kieSession.insert(item);
            kieSession.fireAllRules();
        }

        this.itemService.saveAll(allItems);

        List<Item> itemsForRefill = allItems.stream().filter(Item::isRefill).collect(Collectors.toList());

        return new ResponseEntity<>(itemsForRefill, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> refillItem(@PathVariable Long id, @RequestBody String addAmount) {
        this.itemService.addAmount(id, Integer.parseInt(addAmount));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
