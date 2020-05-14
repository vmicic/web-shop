package com.ftn.webshop.repositories;

import com.ftn.webshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByCodeIgnoreCase(String code);
}
