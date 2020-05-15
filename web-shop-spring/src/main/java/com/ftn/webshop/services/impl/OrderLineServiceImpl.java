package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.OrderLine;
import com.ftn.webshop.repositories.OrderLineRepository;
import com.ftn.webshop.services.OrderLineService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public OrderLineServiceImpl(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public OrderLine save(OrderLine orderLine) {
        return this.orderLineRepository.save(orderLine);
    }

    @Override
    public OrderLine findById(Long id) {
        Optional<OrderLine> orderLine = this.orderLineRepository.findById(id);

        return orderLine.orElse(null);
    }


}
