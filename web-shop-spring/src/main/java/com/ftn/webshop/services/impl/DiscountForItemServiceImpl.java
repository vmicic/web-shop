package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.DiscountForItem;
import com.ftn.webshop.services.DiscountForItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DiscountForItemServiceImpl implements DiscountForItemService {

    private static Logger logger = LoggerFactory.getLogger(DiscountForItemServiceImpl.class);

    @Override
    public void createDiscountForItem(DiscountForItem discountForItem) {
        logger.info(discountForItem.toString());
    }
}
