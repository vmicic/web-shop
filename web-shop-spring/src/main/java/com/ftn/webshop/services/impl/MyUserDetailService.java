package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.User;
import com.ftn.webshop.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findOneByUsernameIgnoreCase(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        } else {
            return user;
        }
    }
}
