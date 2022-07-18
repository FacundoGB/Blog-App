package com.spring.blogapp.dto;

import com.spring.blogapp.entity.Comment;
import lombok.*;

import java.util.Set;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;
}
