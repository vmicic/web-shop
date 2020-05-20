package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Discount;
import com.ftn.webshop.repositories.DiscountRepository;
import com.ftn.webshop.services.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private static Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount createDiscount(Discount discount) {
        logger.info(discount.toString());
        return this.discountRepository.save(discount);
    }
}
