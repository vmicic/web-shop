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
public class OrderServiceTest {


    private static Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

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
    public void checkDiscountsForMore20ItemsAndNoMassConsumption() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(30);

        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemId(2L);
        orderLineDTO2.setQuantity(25);

        orderLines.add(orderLineDTO1);
        orderLines.add(orderLineDTO2);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order = orderService.createOrder(orderDTO);
        orderService.processOrder(order, AuthenticationController.getKieSession());

        Order order1 = orderService.findById(order.getId());
        //no discount for milk, but there is for speakers
        assertEquals(0, order1.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(1, order1.getOrderLines().get(1).getDiscountsForItem().size());
    }

    @Test
    public void checkDiscountsForMore5ItemsAndIT() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(30);

        //bluetooth speakers
        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemId(2L);
        orderLineDTO2.setQuantity(17);

        //intel desktop pc
        OrderLineDTO orderLineDTO3 = new OrderLineDTO();
        orderLineDTO3.setItemId(3L);
        orderLineDTO3.setQuantity(5);


        //laptop rog strix
        OrderLineDTO orderLineDTO4 = new OrderLineDTO();
        orderLineDTO4.setItemId(4L);
        orderLineDTO4.setQuantity(7);

        orderLines.add(orderLineDTO1);
        orderLines.add(orderLineDTO2);
        orderLines.add(orderLineDTO3);
        orderLines.add(orderLineDTO4);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order = orderService.createOrder(orderDTO);
        orderService.processOrder(order, AuthenticationController.getKieSession());

        Order order1 = orderService.findById(order.getId());

        assertEquals(0, order1.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(0, order1.getOrderLines().get(1).getDiscountsForItem().size());
        assertEquals(0, order1.getOrderLines().get(2).getDiscountsForItem().size());
        assertEquals(1, order1.getOrderLines().get(3).getDiscountsForItem().size());
    }

    @Test
    public void checkDiscountMassConsumptionMoreThan5000() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(50);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order = orderService.createOrder(orderDTO);
        orderService.processOrder(order, AuthenticationController.getKieSession());

        Order order1 = orderService.findById(order.getId());

        assertEquals(1, order1.getOrderLines().get(0).getDiscountsForItem().size());
    }

    @Test
    public void testActivationGroup() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(4L);
        orderLineDTO1.setQuantity(50);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order = orderService.createOrder(orderDTO);
        orderService.processOrder(order, AuthenticationController.getKieSession());

        Order order1 = orderService.findById(order.getId());

        assertEquals(1, order1.getOrderLines().get(0).getDiscountsForItem().size());
    }



}
