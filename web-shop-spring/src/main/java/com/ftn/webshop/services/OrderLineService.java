package com.ftn.webshop.services;

import com.ftn.webshop.domain.OrderLine;

public interface OrderLineService {

    OrderLine save(OrderLine orderLine);

    OrderLine findById(Long id);
}
