package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.CustomerCategory;
import com.ftn.webshop.services.CustomerCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer-category")
public class CustomerCategoryController {

    private final CustomerCategoryService customerCategoryService;


    public CustomerCategoryController(CustomerCategoryService customerCategoryService) {
        this.customerCategoryService = customerCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CustomerCategory> customerCategories = this.customerCategoryService.getAll();

        return new ResponseEntity<>(customerCategories, HttpStatus.OK);
    }
}
