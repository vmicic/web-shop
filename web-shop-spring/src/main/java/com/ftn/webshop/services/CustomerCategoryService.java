package com.ftn.webshop.services;

import com.ftn.webshop.domain.CustomerCategory;

import java.util.List;

public interface CustomerCategoryService {

    CustomerCategory findById(Long id);

    List<CustomerCategory> getAll();
}
