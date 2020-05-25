package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Discount;
import com.ftn.webshop.domain.Order;
import com.ftn.webshop.repositories.DiscountRepository;
import com.ftn.webshop.services.DiscountService;
import com.ftn.webshop.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private static Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

    private final DiscountRepository discountRepository;

    private final OrderService orderService;

    public DiscountServiceImpl(DiscountRepository discountRepository, OrderService orderService) {
        this.discountRepository = discountRepository;
        this.orderService = orderService;
    }

    @Override
    public void createDiscount(Discount discount) {
        logger.info(discount.toString());

        this.discountRepository.save(discount);
    }

    @Override
    public List<Discount> findAllByOrderId(Long id) {
        return this.discountRepository.findAllByOrder_Id(id);
    }
}
