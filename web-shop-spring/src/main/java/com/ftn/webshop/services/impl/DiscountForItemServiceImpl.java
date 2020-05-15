package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.DiscountForItem;
import com.ftn.webshop.domain.OrderLine;
import com.ftn.webshop.repositories.DiscountForItemRepository;
import com.ftn.webshop.services.DiscountForItemService;
import com.ftn.webshop.services.OrderLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DiscountForItemServiceImpl implements DiscountForItemService {

    private static Logger logger = LoggerFactory.getLogger(DiscountForItemServiceImpl.class);

    private final DiscountForItemRepository discountForItemRepository;
    private final OrderLineService orderLineService;

    public DiscountForItemServiceImpl(DiscountForItemRepository discountForItemRepository, OrderLineService orderLineService) {
        this.discountForItemRepository = discountForItemRepository;
        this.orderLineService = orderLineService;
    }

    @Override
    public void createDiscountForItem(DiscountForItem discountForItem) {
        //TODO
        //discountForItem generate Code
        logger.info(discountForItem.toString());
        this.discountForItemRepository.save(discountForItem);
    }
}
