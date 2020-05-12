package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("cart")
public class CartController {

    private static Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping
    public ResponseEntity createCart(@RequestBody Cart cart) {


        return new ResponseEntity(HttpStatus.OK);
    }
}
