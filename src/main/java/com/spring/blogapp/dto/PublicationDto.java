package com.spring.blogapp.dto;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}
