package com.ftn.webshop.repositories;

import com.ftn.webshop.domain.DiscountForItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountForItemRepository extends JpaRepository<DiscountForItem, Long> {
    List<DiscountForItem> findAllByOrderLine_Id(Long id);
}
