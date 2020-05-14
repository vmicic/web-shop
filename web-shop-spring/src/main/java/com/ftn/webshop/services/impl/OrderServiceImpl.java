package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.OrderLine;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.repositories.OrderRepository;
import com.ftn.webshop.services.ItemService;
import com.ftn.webshop.services.OrderService;
import com.ftn.webshop.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;
    private final ItemService itemService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.itemService = itemService;
    }


    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();

        //TODO fix this later
        //order.setOrderId(1L);
        order.setLocalDateTime(LocalDateTime.now());
        order.setUser(userService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        List<OrderLine> orderLines = new ArrayList<>();
        Integer counter = 1;

        for(OrderLineDTO orderLineDTO : orderDTO.getOrderLines()) {
            OrderLine orderLine = new OrderLine();
            orderLine.setItem(itemService.findByCode(orderLineDTO.getCode()));
            orderLine.setQuantity(orderLineDTO.getQuantity());
            orderLine.setSerialNumber(counter);

            counter++;
        }

        return order;


    }
}
