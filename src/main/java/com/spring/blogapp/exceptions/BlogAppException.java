package com.spring.blogapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @NoArgsConstructor
@Getter@Setter
public class BlogAppException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public BlogAppException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message;
        this.message = message1;
    }

}
