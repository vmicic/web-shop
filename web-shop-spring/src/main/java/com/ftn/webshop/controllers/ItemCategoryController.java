package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.domain.dto.ItemCategoryDTO;
import com.ftn.webshop.domain.dto.PromotionCreateDTO;
import com.ftn.webshop.services.ItemCategoryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item-category")
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;


    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ItemCategory> itemCategories = this.itemCategoryService.getAll();

        return new ResponseEntity<>(itemCategories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createItemCategory(@RequestBody ItemCategoryDTO itemCategoryDTO) {
        ItemCategory itemCategory = this.itemCategoryService.create(itemCategoryDTO);

        return new ResponseEntity<>(itemCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ItemCategoryDTO itemCategoryDTO) {
        ItemCategory itemCategory = this.itemCategoryService.update(id,itemCategoryDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
