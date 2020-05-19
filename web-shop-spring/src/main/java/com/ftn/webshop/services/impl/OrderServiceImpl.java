package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Item;
import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.OrderLine;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.repositories.OrderRepository;
import com.ftn.webshop.services.ItemService;
import com.ftn.webshop.services.OrderLineService;
import com.ftn.webshop.services.OrderService;
import com.ftn.webshop.services.UserService;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final UserService userService;
    private final ItemService itemService;
    private final OrderLineService orderLineService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ItemService itemService, OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.itemService = itemService;
        this.orderLineService = orderLineService;
    }


    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();

        //TODO fix this later
        order.setLocalDateTime(LocalDateTime.now());
        order.setDate(new Date());
        order.setUser(userService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        List<OrderLine> orderLines = new ArrayList<>();
        Integer counter = 1;

        double priceBeforeDiscount = 0.;
        double orderLinePrice;

        for(OrderLineDTO orderLineDTO : orderDTO.getOrderLines()) {
            OrderLine orderLine = orderLineService.createOrderLine(orderLineDTO);

            priceBeforeDiscount += orderLine.getPriceTotal();

            orderLine.setSerialNumber(counter);
            orderLine.setOrder(order);
            orderLines.add(orderLine);

            counter++;
        }

        order.setOrderLines(orderLines);
        //order.setPriceBeforeDiscount(priceBeforeDiscount);

        this.orderRepository.save(order);

        for(OrderLine orderLine: order.getOrderLines()) {
            this.orderLineService.save(orderLine);
        }

        logger.info(order.toString());
        return order;
    }

    @Override
    public Order processOrder(Order order, KieSession kieSession) {
        for(OrderLine orderLine : order.getOrderLines()) {
            kieSession.insert(orderLine);
        }

        kieSession.insert(order);
        kieSession.fireAllRules();
        return null;
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);

        return order.orElse(null);
    }

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }
}
