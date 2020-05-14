package com.ftn.webshop.repositories;

import com.ftn.webshop.domain.DiscountForItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountForItemRepository extends JpaRepository<DiscountForItem, Long> {
}
