package com.ftn.webshop.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private List<OrderLineDTO> orderLines;

    public OrderDTO() {
    }

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                ", orderLines=" + orderLines +
                '}';
    }
}
