package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    public ResponseEntity createOrder(Order order) {
        logger.info(order.toString());

        return null;
    }


}
