package com.ftn.webshop.services;

import com.ftn.webshop.domain.User;

public interface UserService {

    User findOneByUsername(String email);
}
