package com.ftn.webshop.services;

import com.ftn.webshop.domain.OrderLine;
import com.ftn.webshop.domain.dto.OrderLineDTO;

public interface OrderLineService {

    OrderLine save(OrderLine orderLine);

    OrderLine findById(Long id);

    OrderLine createOrderLine(OrderLineDTO orderLineDTO);

}
