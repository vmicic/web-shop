package com.ftn.webshop.services;

import com.ftn.webshop.domain.Authority;

public interface AuthorityService {
    Authority findById(Long id);
    Authority findByName(String name);
}
