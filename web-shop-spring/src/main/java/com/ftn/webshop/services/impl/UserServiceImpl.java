package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.User;
import com.ftn.webshop.repositories.UserRepository;
import com.ftn.webshop.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }


    @Override
    public User findOneByUsername(String email) {
        return userRepository.findOneByUsernameIgnoreCase(email).orElse(null);
    }
}
