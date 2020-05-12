package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Authority;
import com.ftn.webshop.repositories.AuthorityRepository;
import com.ftn.webshop.services.AuthorityService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }

    @Override
    public Authority findByName(String name) {
        return authorityRepository.findByName(name);
    }
}
