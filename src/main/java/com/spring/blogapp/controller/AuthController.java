package com.spring.blogapp.controller;

import com.spring.blogapp.dto.LoginDto;
import com.spring.blogapp.dto.RegisterDto;
import com.spring.blogapp.entity.MyUser;
import com.spring.blogapp.entity.Role;
import com.spring.blogapp.repository.RolRepository;
import com.spring.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        //We save an athenticate object by obtaining a new usernate & password athenticated.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        //we establish the authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("You've successfully logged in!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("This username already exists", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("This email is already in use", HttpStatus.BAD_REQUEST);
        }

        MyUser user = new MyUser();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = rolRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        //singleton return a single role

        userRepository.save(user);
        return new ResponseEntity<>("User successfully registered!", HttpStatus.OK);
    }

}
