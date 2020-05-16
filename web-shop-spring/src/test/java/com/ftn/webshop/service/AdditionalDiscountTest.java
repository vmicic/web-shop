package com.ftn.webshop.service;

import com.ftn.webshop.WebShopApplication;
import com.ftn.webshop.controllers.AuthenticationController;
import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.security.auth.JwtAuthenticationRequest;
import com.ftn.webshop.services.OrderService;
import org.drools.core.ClockType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.time.SessionPseudoClock;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebShopApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdditionalDiscountTest {

    private static Logger logger = LoggerFactory.getLogger(AdditionalDiscountTest.class);

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
    public void checkDiscountsForMore5ItemsAndIT() {

        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(30);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);
        orderService.processOrder(order1, AuthenticationController.getKieSession());


        List<OrderLineDTO> orderLines2 = new ArrayList<>();

        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemId(2L);
        orderLineDTO2.setQuantity(10);

        OrderLineDTO orderLineDTO3 = new OrderLineDTO();
        orderLineDTO3.setItemId(3L);
        orderLineDTO3.setQuantity(2);

        orderLines2.add(orderLineDTO2);
        orderLines2.add(orderLineDTO3);

        OrderDTO orderDTO2 = new OrderDTO();

        orderDTO2.setOrderLines(orderLines2);

        Order order2 = orderService.createOrder(orderDTO2);
        order2.setDate(new Date( new Date().getTime() + 2L*24*60*60*1000 ));
        orderService.saveOrder(order2);
        orderService.processOrder(order2, AuthenticationController.getKieSession());

        //same order < then 15 days
        Order order3 = orderService.createOrder(orderDTO2);
        order3.setDate(new Date( new Date().getTime() + 16L*24*60*60*1000 ));
        orderService.saveOrder(order3);
        orderService.processOrder(order3, AuthenticationController.getKieSession());

        //same item ordered > then 15 days
        Order order4 = orderService.createOrder(orderDTO2);
        order4.setDate(new Date( new Date().getTime() + 40L*24*60*60*1000 ));
        orderService.saveOrder(order4);
        orderService.processOrder(order4, AuthenticationController.getKieSession());


        assertEquals(0, order1.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(0, order2.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(1, order3.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(0, order4.getOrderLines().get(0).getDiscountsForItem().size());
    }
}
