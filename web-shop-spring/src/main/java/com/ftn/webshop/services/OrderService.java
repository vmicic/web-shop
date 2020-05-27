package com.ftn.webshop.services;

import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderDTOProcessed;
import org.kie.api.runtime.KieSession;

public interface OrderService {

    Order createOrder(OrderDTO orderDTO);

    Order save(Order order);

    Order processOrder(Order order, KieSession kieSession);

    Order findById(Long id);

    OrderDTOProcessed createDTO(Order order);
}
