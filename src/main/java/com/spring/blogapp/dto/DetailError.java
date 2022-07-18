package com.spring.blogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class DetailError {

    private Date timeStamp;
    private String message;
    private String detail;

}
