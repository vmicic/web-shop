package com.ftn.webshop.controllers;

import com.ftn.webshop.WebShopApplication;
import com.ftn.webshop.domain.User;
import com.ftn.webshop.domain.UserTokenState;
import com.ftn.webshop.domain.dto.UserDTO;
import com.ftn.webshop.security.TokenUtils;
import com.ftn.webshop.security.auth.JwtAuthenticationRequest;
import com.ftn.webshop.services.DiscountForItemService;
import com.ftn.webshop.services.OrderLineService;
import com.ftn.webshop.services.UserService;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private static KieSession kieSession;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenUtils tokenUtils;

    private final DiscountForItemService discountForItemService;
    private final OrderLineService orderLineService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenUtils tokenUtils, DiscountForItemService discountForItemService, OrderLineService orderLineService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenUtils = tokenUtils;
        this.discountForItemService = discountForItemService;
        this.orderLineService = orderLineService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody JwtAuthenticationRequest authenticationRequest) {

        try{
            final Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));


            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userService.findOneByUsername(authenticationRequest.getUsername());
            String jwt = tokenUtils.generateToken(user.getUsername());
            Long expiresIn = tokenUtils.getExpiredIn();

            kieSession = WebShopApplication.kieContainer().newKieSession();
            kieSession.setGlobal("orderLineService", orderLineService);
            kieSession.setGlobal("discountForItemService", discountForItemService);

            return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
        }catch (BadCredentialsException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody UserDTO userDTO) {
        if(userService.userExists(userDTO.getUsername())) {
            return new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(userDTO);

        return new ResponseEntity(HttpStatus.OK);
    }


    public static KieSession getKieSession() {
        return kieSession;
    }



}
