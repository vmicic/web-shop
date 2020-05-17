package com.ftn.webshop.services;

import com.ftn.webshop.domain.User;
import com.ftn.webshop.domain.dto.UserDTO;

public interface UserService {

    User findOneByUsername(String email);

    User createUser(UserDTO userDTO);

    boolean userExists(String username);
}
