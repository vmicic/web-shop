package com.ftn.webshop.services;

import com.ftn.webshop.domain.Order;
import com.ftn.webshop.domain.dto.OrderDTO;

public interface OrderService {

    Order createOrder(OrderDTO orderDTO);
}
