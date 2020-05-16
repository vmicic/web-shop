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
/*        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("com.ftn", "web-shop-drools", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        kbconf.setOption(EventProcessingOption.STREAM);
        KieBase kbase = kContainer.newKieBase(kbconf);

        KieSessionConfiguration ksconf1 = ks.newKieSessionConfiguration();
        ksconf1.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession ksession1 = kbase.newKieSession(ksconf1, null);*/


        List<OrderLineDTO> orderLines = new ArrayList<>();

        //milk
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setItemId(1L);
        orderLineDTO1.setQuantity(30);

        orderLines.add(orderLineDTO1);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderLines(orderLines);

        Order orderOld = orderService.createOrder(orderDTO);
        orderService.processOrder(orderOld, AuthenticationController.getKieSession());

        //ksession1.insert(orderOld);
        //ksession1.fireAllRules();

        List<OrderLineDTO> orderLines2 = new ArrayList<>();

        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        orderLineDTO2.setItemId(2L);
        orderLineDTO2.setQuantity(10);

        orderLines2.add(orderLineDTO2);

        OrderDTO orderDTO2 = new OrderDTO();

        orderDTO2.setOrderLines(orderLines2);

        Order orderNew = orderService.createOrder(orderDTO2);
        orderNew.setDate(new Date( new Date().getTime() + 2L*24*60*60*1000 ));
        orderService.saveOrder(orderNew);
        orderService.processOrder(orderNew, AuthenticationController.getKieSession());


       // ksession1.insert(orderNew);
       // ksession1.fireAllRules();

        //SessionPseudoClock clock = ksession1.getSessionClock();
        //System.out.println(clock.getCurrentTime());
        //clock.advanceTime(50, TimeUnit.DAYS);
        //System.out.println(clock.getCurrentTime());



        orderNew = orderService.createOrder(orderDTO2);
        orderNew.setDate(new Date( new Date().getTime() + 16L*24*60*60*1000 ));
        orderService.saveOrder(orderNew);
        orderService.processOrder(orderNew, AuthenticationController.getKieSession());


        //ksession1.insert(orderNew);
        //ksession1.fireAllRules();

    }
}
