package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.domain.dto.PromotionCreateDTO;
import com.ftn.webshop.domain.dto.PromotionDTO;
import com.ftn.webshop.services.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> createPromotion(@RequestBody PromotionCreateDTO promotionCreateDTO) {
        System.out.println(promotionCreateDTO.toString());
        Promotion promotion = this.promotionService.createPromotion(promotionCreateDTO);

        System.out.println(promotion.toString());

        return null;
    }
}
