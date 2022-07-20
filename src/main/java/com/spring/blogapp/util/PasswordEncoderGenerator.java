package com.spring.blogapp.util;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
    }
}
/**
 * In this package/method we hard code a hashed password, get it hashed in the
 * console, save it on the db with fixed user and admin to test functionality.
 */