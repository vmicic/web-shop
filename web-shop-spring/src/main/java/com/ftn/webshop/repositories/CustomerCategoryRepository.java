package com.ftn.webshop.repositories;

import com.ftn.webshop.domain.CustomerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCategoryRepository extends JpaRepository<CustomerCategory, Long> {

}
