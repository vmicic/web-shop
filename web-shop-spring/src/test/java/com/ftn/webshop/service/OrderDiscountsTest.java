package com.ftn.webshop.service;

import com.ftn.webshop.WebShopApplication;
import com.ftn.webshop.controllers.AuthenticationController;
import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.TypeOfDiscount;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.security.auth.JwtAuthenticationRequest;
import com.ftn.webshop.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebShopApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderDiscountsTest {

    private static Logger logger = LoggerFactory.getLogger(FinalDiscountTest.class);

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
    public void orderCalculatePrice() {
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

        assertNotNull(order1.getPriceBeforeDiscount());

        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemId(2L);
        orderLineDTO2.setQuantity(1);

        orderLines.add(orderLineDTO2);
        orderDTO.setOrderLines(orderLines);

        Order order2 = orderService.createOrder(orderDTO);
        orderService.processOrder(order2, AuthenticationController.getKieSession());

        assertNotNull(order1.getPriceBeforeDiscount());
    }

    @Test
    public void testOrderBasicDiscount() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(2L);
        orderLineDTO1.setQuantity(100);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);
        orderService.processOrder(order1, AuthenticationController.getKieSession());

        order1 = orderService.findById(order1.getId());
        assertEquals(TypeOfDiscount.BASIC, order1.getDiscounts().get(0).getTypeOfDiscountForItem());
    }

    @Test
    public void testOrderLoyaltyDiscount() {
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();
        jwtAuthenticationRequest.setUsername("mika");
        jwtAuthenticationRequest.setPassword("pera");

        authenticationController.login(jwtAuthenticationRequest);

        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(40);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);
        orderService.processOrder(order1, AuthenticationController.getKieSession());

        //it will have two discounts
        order1 = orderService.findById(order1.getId());
        assertEquals((Double) 1., order1.getDiscounts().get(0).getDiscountPercentage());
    }

    @Test
    public void testUserSilverOrGold() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(10);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);
        orderService.processOrder(order1, AuthenticationController.getKieSession());

        order1 = orderService.findById(order1.getId());
        assertEquals((Double) 1., order1.getDiscounts().get(0).getDiscountPercentage());
    }

}
