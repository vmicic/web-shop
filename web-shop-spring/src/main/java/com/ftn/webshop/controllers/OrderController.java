package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO) {
        //logger.info(SecurityContextHolder.getContext().getAuthentication().getName());

        logger.info(orderDTO.toString());

        return null;
    }


}
