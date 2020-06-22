package com.ftn.webshop.repositories;

import com.ftn.webshop.domain.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
}
