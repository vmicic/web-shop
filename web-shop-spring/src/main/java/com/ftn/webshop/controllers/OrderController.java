package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.services.OrderService;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO) {
        //logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
        KieSession kieSession = AuthenticationController.getKieSession();

        logger.info(orderDTO.toString());
        Order order = orderService.createOrder(orderDTO);

        order = orderService.processOrder(order, kieSession);


        return null;
    }


}
