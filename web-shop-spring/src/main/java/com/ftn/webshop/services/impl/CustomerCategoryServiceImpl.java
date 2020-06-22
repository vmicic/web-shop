package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.CustomerCategory;
import com.ftn.webshop.repositories.CustomerCategoryRepository;
import com.ftn.webshop.services.CustomerCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCategoryServiceImpl implements CustomerCategoryService {

    private final CustomerCategoryRepository customerCategoryRepository;


    public CustomerCategoryServiceImpl(CustomerCategoryRepository customerCategoryRepository) {
        this.customerCategoryRepository = customerCategoryRepository;
    }

    @Override
    public CustomerCategory findById(Long id) {
        return this.customerCategoryRepository.findById(id).orElse(null);
    }


    @Override
    public List<CustomerCategory> getAll() {
        return this.customerCategoryRepository.findAll();
    }
}
