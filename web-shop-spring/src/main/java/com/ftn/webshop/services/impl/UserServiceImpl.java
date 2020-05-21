package com.ftn.webshop.services.impl;

import com.ftn.webshop.domain.Authority;
import com.ftn.webshop.domain.User;
import com.ftn.webshop.domain.dto.UserDTO;
import com.ftn.webshop.repositories.UserRepository;
import com.ftn.webshop.security.auth.AuthoritiesConstants;
import com.ftn.webshop.services.AuthorityService;
import com.ftn.webshop.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }


    @Override
    public User findOneByUsername(String email) {
        return userRepository.findOneByUsernameIgnoreCase(email).orElse(null);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDateOfRegistration(new Date());

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.findByName(AuthoritiesConstants.USER));

        user.setAuthorities(authorities);

        return this.userRepository.save(user);

    }

    @Override
    public boolean userExists(String username) {
        Optional<User> user = this.userRepository.findOneByUsernameIgnoreCase(username);

        return user.isPresent();
    }
}
