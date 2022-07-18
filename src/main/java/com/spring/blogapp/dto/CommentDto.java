package com.spring.blogapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter@Setter@NoArgsConstructor
public class CommentDto {

    private Long id;

    @NotEmpty(message = "Name should not be empty or null")
    private String name;

    @NotEmpty(message = "Mail should not be empty or null")
    @Email
    private String email;

    @NotEmpty(message = "Mail should not be empty or null")
    @Size(min = 10, message = "body should not be empty or null, and must be at least 10 characters long")
    private String body;
}
