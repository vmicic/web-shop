package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.ItemCategory;
import com.ftn.webshop.services.ItemCategoryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
