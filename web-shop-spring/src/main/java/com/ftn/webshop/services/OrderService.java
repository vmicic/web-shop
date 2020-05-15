package com.ftn.webshop.services;

import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.dto.OrderDTO;
import org.kie.api.runtime.KieSession;

public interface OrderService {

    Order createOrder(OrderDTO orderDTO);

    Order processOrder(Order order, KieSession kieSession);

    Order findById(Long id);
}
