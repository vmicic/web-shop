package com.ftn.webshop.security.auth;

import com.ftn.webshop.security.TokenUtils;
import com.ftn.webshop.services.impl.MyUserDetailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenUtils tokenUtils;
    private MyUserDetailService myUserDetailsService;

    public TokenAuthenticationFilter(TokenUtils tokenUtils, MyUserDetailService myUserDetailsService) {
        this.tokenUtils = tokenUtils;
        this.myUserDetailsService = myUserDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String username;
        String authToken = tokenUtils.getToken(request);

        if (authToken != null && !tokenUtils.isTokenExpired(authToken)) {

            username = tokenUtils.getUsernameFromToken(authToken);
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

            if (tokenUtils.validateToken(authToken, userDetails)) {
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        chain.doFilter(request, response);

    }
}
