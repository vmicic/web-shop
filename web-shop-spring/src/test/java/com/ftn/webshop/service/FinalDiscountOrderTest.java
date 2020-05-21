package com.ftn.webshop.service;

import com.ftn.webshop.WebShopApplication;
import com.ftn.webshop.controllers.AuthenticationController;
import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.security.auth.JwtAuthenticationRequest;
import com.ftn.webshop.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebShopApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FinalDiscountOrderTest {

    private static Logger logger = LoggerFactory.getLogger(FinalDiscountOrderTest.class);

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


        //AuthenticationController.getKieSession().addEventListener(new DebugAgendaEventListener());
        //AuthenticationController.getKieSession().addEventListener(new DebugRuleRuntimeEventListener());
    }

    @Test
    public void calculateAllDiscountsForOrder() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(2000);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);
        orderService.processOrder(order1, AuthenticationController.getKieSession());

        order1 = orderService.findById(order1.getId());
        assertEquals((Double) 6.0, order1.getPercentageDiscount());
    }

    @Test
    public void awardBonusPoints() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(50);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);
        orderService.processOrder(order1, AuthenticationController.getKieSession());

        //order1 = orderService.findById(order1.getId());
        //assertEquals((Double) 6.0, order1.getPercentageDiscount());
    }
}
