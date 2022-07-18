package com.spring.blogapp.controller;

import com.spring.blogapp.dto.CommentDto;
import com.spring.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable(value = "publicationId") long publicationId,
                                                  @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(service.createComment(publicationId, commentDto),
                HttpStatus.CREATED);
    }
}
