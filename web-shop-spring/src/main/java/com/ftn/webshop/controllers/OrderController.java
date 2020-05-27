package com.ftn.webshop.controllers;

import com.ftn.webshop.domain.*;
import com.ftn.webshop.domain.dto.OrderDTO;
import com.ftn.webshop.domain.dto.OrderDTOProcessed;
import com.ftn.webshop.services.DiscountForItemService;
import com.ftn.webshop.services.DiscountService;
import com.ftn.webshop.services.OrderLineService;
import com.ftn.webshop.services.OrderService;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderLineService orderLineService;
    private final DiscountForItemService discountForItemService;
    private final DiscountService discountService;

    public OrderController(OrderService orderService, OrderLineService orderLineService, DiscountForItemService discountForItemService, DiscountService discountService) {
        this.orderService = orderService;
        this.orderLineService = orderLineService;
        this.discountForItemService = discountForItemService;
        this.discountService = discountService;
    }


    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        //logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
        KieSession kieSession = AuthenticationController.getKieSession();

        logger.info(orderDTO.toString());
        Order order = orderService.createOrder(orderDTO);

        orderService.processOrder(order, kieSession);
        //logger.info("Processed order: " + order.toString());

        Order orderDb = orderService.findById(order.getId());

        for(int i = 0; i < orderDb.getOrderLines().size(); i++) {
            OrderLine orderLine = orderDb.getOrderLines().get(i);
            List<DiscountForItem> discountForItems = discountForItemService.findAllByOrderLineId(orderLine.getId());
            orderLine.setDiscountsForItem(discountForItems);
            orderLineService.save(orderLine);
        }

        List<Discount> discounts = discountService.findAllByOrderId(orderDb.getId());
        orderDb.setDiscounts(discounts);

        OrderDTOProcessed orderDTOProcessed = orderService.createDTO(orderDb);

        return new ResponseEntity<>(orderDTOProcessed, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(orderDTO);

        Discount discount = new Discount();

        discount.setOrder(order);
        discount.setDiscountPercentage(2.);
        discount.setTypeOfDiscountForItem(TypeOfDiscount.BASIC);
        discountService.createDiscount(discount);

        //order.addDiscount(discount);
        Order order2 = orderService.findById(order.getId());
        return new ResponseEntity<>(order2, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllOrders() {
        return null;
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity confirmOrder(@PathVariable Long id, @RequestBody Integer awardPoints) {

        Order order = orderService.findById(id);
        order.setBonusPointsSpent(awardPoints);
        order.setOrderState(OrderState.ORDERED);
        orderService.save(order);

        return null;
    }


}
