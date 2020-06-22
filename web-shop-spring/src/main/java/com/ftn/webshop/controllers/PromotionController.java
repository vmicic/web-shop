package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.services.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;


    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Promotion> promotions = this.promotionService.findAll();

        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }
}
