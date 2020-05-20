package com.ftn.webshop.repositories;

import com.ftn.webshop.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
