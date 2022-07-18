package com.spring.blogapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

/*
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
*/

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }



/*    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
        if(!passwordEncoder().matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong Password!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(), userDetails.getAuthorities());
    }*/
}
