package com.spring.blogapp.dto;

import com.spring.blogapp.entity.Comment;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {


    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Publications title is too small. Use at least 3 letters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Publications description is too small. Use at least 3 letters")
    private String description;

    @NotEmpty
    private String content;

    private Set<Comment> comments;
}
