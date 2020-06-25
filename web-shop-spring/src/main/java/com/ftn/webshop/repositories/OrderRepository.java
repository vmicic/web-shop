package com.ftn.webshop.repositories;

import com.ftn.webshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderStateNotNull();
}
