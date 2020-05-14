package com.ftn.webshop.service;

import com.ftn.webshop.WebShopApplication;
import com.ftn.webshop.controllers.AuthenticationController;
import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.OrderLine;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.security.auth.JwtAuthenticationRequest;
import com.ftn.webshop.services.OrderService;
import io.jsonwebtoken.Jwt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebShopApplication.class)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    AuthenticationController authenticationController;

    @Before
    public void login() {
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();
        jwtAuthenticationRequest.setUsername("pera");
        jwtAuthenticationRequest.setPassword("pera");

        authenticationController.login(jwtAuthenticationRequest);
    }

    @Test
    public void checkTest() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemCode("750");
        orderLineDTO1.setQuantity(30);

        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemCode("800");
        orderLineDTO2.setQuantity(25);

        orderLines.add(orderLineDTO1);
        orderLines.add(orderLineDTO2);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order = orderService.createOrder(orderDTO);

        orderService.processOrder(order, AuthenticationController.getKieSession());

    }
}
