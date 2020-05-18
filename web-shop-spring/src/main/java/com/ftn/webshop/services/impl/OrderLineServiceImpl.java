package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Item;
import com.ftn.webshop.domain.OrderLine;
import com.ftn.webshop.domain.dto.OrderLineDTO;
import com.ftn.webshop.repositories.OrderLineRepository;
import com.ftn.webshop.services.ItemService;
import com.ftn.webshop.services.OrderLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private static Logger logger = LoggerFactory.getLogger(OrderLineServiceImpl.class);

    private final OrderLineRepository orderLineRepository;
    private final ItemService itemService;

    public OrderLineServiceImpl(OrderLineRepository orderLineRepository, ItemService itemService) {
        this.orderLineRepository = orderLineRepository;
        this.itemService = itemService;
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

    @Override
    public OrderLine createOrderLine(OrderLineDTO orderLineDTO) {
        OrderLine orderLine = new OrderLine();

        Item item = itemService.findItemById(orderLineDTO.getItemId());

        double orderLinePrice;
        orderLinePrice = item.getPrice() * orderLineDTO.getQuantity();

        orderLine.setPriceTotal(orderLinePrice);
        orderLine.setItem(item);
        orderLine.setQuantity(orderLineDTO.getQuantity());
        orderLine.setPricePerUnit(item.getPrice());

        return orderLine;
    }


}
