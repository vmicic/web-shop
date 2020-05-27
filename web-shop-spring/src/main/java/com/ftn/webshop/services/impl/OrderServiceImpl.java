package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.*;
import com.ftn.webshop.domain.dto.*;
import com.ftn.webshop.repositories.OrderRepository;
import com.ftn.webshop.services.ItemService;
import com.ftn.webshop.services.OrderLineService;
import com.ftn.webshop.services.OrderService;
import com.ftn.webshop.services.UserService;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final UserService userService;
    private final ItemService itemService;
    private final OrderLineService orderLineService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ItemService itemService, OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.itemService = itemService;
        this.orderLineService = orderLineService;
    }


    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();

        //TODO fix this later
        order.setLocalDateTime(LocalDateTime.now());
        order.setDate(new Date());
        order.setUser(userService.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        List<OrderLine> orderLines = new ArrayList<>();
        Integer counter = 1;

        double priceBeforeOrderLineDiscount = 0.;
        double orderLinePrice;

        for(OrderLineDTO orderLineDTO : orderDTO.getOrderLines()) {
            OrderLine orderLine = orderLineService.createOrderLine(orderLineDTO);

            priceBeforeOrderLineDiscount += orderLine.getPriceTotal();

            orderLine.setSerialNumber(counter);
            orderLine.setOrder(order);
            orderLines.add(orderLine);

            counter++;
        }

        order.setOrderLines(orderLines);
        order.setPriceBeforeOrderLineDiscount(priceBeforeOrderLineDiscount);

        this.orderRepository.save(order);

        for(OrderLine orderLine: order.getOrderLines()) {
            this.orderLineService.save(orderLine);
        }

        logger.info(order.toString());
        return order;
    }

    @Override
    public Order processOrder(Order order, KieSession kieSession) {
        for(OrderLine orderLine : order.getOrderLines()) {
            kieSession.insert(orderLine);
        }

        kieSession.insert(order);
        kieSession.fireAllRules();
        return order;
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);

        return order.orElse(null);
    }

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public OrderDTOProcessed createDTO(Order order) {
        OrderDTOProcessed orderDTO = new OrderDTOProcessed();

        orderDTO.setOrderId(order.getId());
        orderDTO.setPercentageDiscount(order.getPercentageDiscount());
        orderDTO.setPriceBeforeAnyDiscount(order.getPriceBeforeOrderLineDiscount());
        orderDTO.setPriceAfterOrderLineDiscounts(order.getPriceBeforeDiscount());
        orderDTO.setPriceAfterAllDiscounts(order.getPriceAfterDiscount());
        orderDTO.setAwardPoints(order.getUser().getAwardPoints());
        orderDTO.setAwardPointsEarned(order.getBonusPointsAward());

        if(order.getBonusPointsSpent() != null) {
            orderDTO.setAwardPointsSpent(order.getBonusPointsSpent());
        }

        List<OrderLineDTO> orderLineDTOs = new ArrayList<>();

        //order line setup
        for(OrderLine orderLine : order.getOrderLines()) {
            OrderLineDTO orderLineDTO = new OrderLineDTO();

            orderLineDTO.setPricePerUnit(orderLine.getPricePerUnit());
            orderLineDTO.setPriceAfterDiscount(orderLine.getPriceTotalFinal());
            orderLineDTO.setItemCategoryName(orderLine.getItem().getCategory().getName());
            orderLineDTO.setQuantity(orderLine.getQuantity());
            orderLineDTO.setItemName(orderLine.getItem().getName());
            orderLineDTO.setSerialNumber(orderLine.getSerialNumber());
            orderLineDTO.setPercentageDiscount(orderLine.getPercentageDiscount());

            List<DiscountForItemDTO> discountForItemDTOs = new ArrayList<>();

            for(DiscountForItem discountForItem : orderLine.getDiscountsForItem()) {
                DiscountForItemDTO discountForItemDTO = new DiscountForItemDTO();

                discountForItemDTO.setId(discountForItem.getId());
                discountForItemDTO.setDiscountPercentage(discountForItem.getDiscountPercentage());
                discountForItemDTO.setTypeOfDiscount(discountForItem.getTypeOfDiscount());

                discountForItemDTOs.add(discountForItemDTO);
            }
            orderLineDTO.setDiscountForItemDTOs(discountForItemDTOs);

            orderLineDTOs.add(orderLineDTO);
        }

        orderDTO.setOrderLineDTOs(orderLineDTOs);

        List<DiscountDTO> discountDTOs = new ArrayList<>();

        for(Discount discount : order.getDiscounts()) {
            DiscountDTO discountDTO = new DiscountDTO();

            discountDTO.setId(discount.getId());
            discountDTO.setDiscountPercentage(discount.getDiscountPercentage());
            discountDTO.setTypeOfDiscount(discount.getTypeOfDiscountForItem());

            discountDTOs.add(discountDTO);
        }

        orderDTO.setDiscountDTOs(discountDTOs);

        return orderDTO;
    }
}
