package com.ftn.webshop.service;

import com.ftn.webshop.WebShopApplication;
import com.ftn.webshop.controllers.AuthenticationController;
import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.Promotion;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.security.auth.JwtAuthenticationRequest;
import com.ftn.webshop.services.OrderService;
import com.ftn.webshop.services.PromotionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebShopApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdditionalDiscountTest {

    private static Logger logger = LoggerFactory.getLogger(AdditionalDiscountTest.class);

    @Autowired
    OrderService orderService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    AuthenticationController authenticationController;

    @Before
    public void login() {
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();
        jwtAuthenticationRequest.setUsername("pera");
        jwtAuthenticationRequest.setPassword("pera");

        authenticationController.login(jwtAuthenticationRequest);

        Promotion promotion1 = promotionService.findById(1L);
        Promotion promotion2 = promotionService.findById(2L);

        AuthenticationController.getKieSession().insert(promotion1);
        AuthenticationController.getKieSession().insert(promotion2);
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
        orderService.save(order2);
        orderService.processOrder(order2, AuthenticationController.getKieSession());

        //same order < then 15 days
        Order order3 = orderService.createOrder(orderDTO2);
        order3.setDate(new Date( new Date().getTime() + 16L*24*60*60*1000 ));
        orderService.save(order3);
        orderService.processOrder(order3, AuthenticationController.getKieSession());

        //same item ordered > then 15 days
        Order order4 = orderService.createOrder(orderDTO2);
        order4.setDate(new Date( new Date().getTime() + 50L*24*60*60*1000 ));
        orderService.save(order4);
        orderService.processOrder(order4, AuthenticationController.getKieSession());


        assertEquals(0, order1.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(0, order2.getOrderLines().get(0).getDiscountsForItem().size());

        //applied to first additional discounts
        assertEquals(2, order3.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(0, order4.getOrderLines().get(0).getDiscountsForItem().size());
    }

    @Test
    public void additionalDiscountForItemsFromSameCategoryBoughtInLast30Days() {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(3);

        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemId(2L);
        orderLineDTO2.setQuantity(1);

        orderLines.add(orderLineDTO1);
        orderLines.add(orderLineDTO2);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);
        orderService.processOrder(order1, AuthenticationController.getKieSession());

        List<OrderLineDTO> orderLines2 = new ArrayList<>();

        OrderLineDTO orderLineDTO3 = new OrderLineDTO();
        orderLineDTO3.setItemId(2L);
        orderLineDTO3.setQuantity(3);

        OrderLineDTO orderLineDTO4 = new OrderLineDTO();
        orderLineDTO4.setItemId(3L);
        orderLineDTO4.setQuantity(1);

        orderLines2.add(orderLineDTO3);
        orderLines2.add(orderLineDTO4);

        OrderDTO orderDTO2 = new OrderDTO();

        orderDTO2.setOrderLines(orderLines2);

        Order order2 = orderService.createOrder(orderDTO2);
        order2.setDate(new Date( new Date().getTime() + 20L*24*60*60*1000 ));
        orderService.save(order2);
        orderService.processOrder(order2, AuthenticationController.getKieSession());


        assertEquals(1, order2.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(0, order2.getOrderLines().get(1).getDiscountsForItem().size());
    }

    @Test
    public void testPromotion() throws ParseException {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk mass consumption
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(3);


        //tv not included in first promotion
        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemId(5L);
        orderLineDTO2.setQuantity(1);

        orderLines.add(orderLineDTO1);
        orderLines.add(orderLineDTO2);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order order1 = orderService.createOrder(orderDTO);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dateString = "2020-12-21 10:00";
        Date date = dateFormat.parse(dateString);
        order1.setDate(date);
        orderService.save(order1);

        orderService.processOrder(order1, AuthenticationController.getKieSession());


        assertEquals(1, order1.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(0, order1.getOrderLines().get(1).getDiscountsForItem().size());

        List<OrderLineDTO> orderLines2 = new ArrayList<>();

        //milk mass consumption
        OrderLineDTO orderLineDTO3 = new OrderLineDTO();
        orderLineDTO3.setItemId(1L);
        orderLineDTO3.setQuantity(3);


        //tv not included in first promotion
        OrderLineDTO orderLineDTO4 = new OrderLineDTO();
        orderLineDTO4.setItemId(3L);
        orderLineDTO4.setQuantity(1);

        orderLines2.add(orderLineDTO3);
        orderLines2.add(orderLineDTO4);

        OrderDTO orderDT2 = new OrderDTO();

        orderDT2.setOrderLines(orderLines2);

        Order order2 = orderService.createOrder(orderDT2);

        String dateString2 = "2020-10-20 10:00";
        Date date2 = dateFormat.parse(dateString2);
        order2.setDate(date2);
        orderService.save(order2);

        orderService.processOrder(order2, AuthenticationController.getKieSession());

        assertEquals(0, order2.getOrderLines().get(0).getDiscountsForItem().size());
        assertEquals(1, order2.getOrderLines().get(1).getDiscountsForItem().size());
    }
}
