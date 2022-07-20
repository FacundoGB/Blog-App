package com.spring.blogapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginDto {

    private String usernameOrEmail;
    private String password;

}
