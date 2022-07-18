package com.spring.blogapp.controller;

import com.spring.blogapp.dto.CommentDto;
import com.spring.blogapp.entity.Comment;
import com.spring.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping("/publications/{publicationId}/comments")
    public List<CommentDto> listCommentsByPublicationId(@PathVariable(value = "publicationId") Long publicationId){
        return service.getCommentByPublicationID(publicationId);
    }

    @GetMapping("/publications/{publicationId}/comments/{commentId}")
    public ResponseEntity<CommentDto> obtainCommentById(@PathVariable(value = "publicationId") Long publicationId,
                                                        @PathVariable(value = "commentId") Long commentId) {
        CommentDto commentDto = service.getCommentById(publicationId, commentId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    //@Valid must be next to @RequestBody
    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable(value = "publicationId") long publicationId,
                                                  @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(service.createComment(publicationId, commentDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/publications/{publicationId}/comments/{commentId}")
    public ResponseEntity<CommentDto> modifyComment(@PathVariable(value = "publicationId") Long publicationId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        CommentDto modifiedComment = service.modifyComment(publicationId, commentId, commentDto);

        return new ResponseEntity<>(modifiedComment, HttpStatus.OK);
    }

    @DeleteMapping("/publications/{publicationId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "publicationId") Long publicationId,
                                                @PathVariable(value = "commentId") Long commentId) {
        service.deleteComment(publicationId, commentId);
        return new ResponseEntity<>("Comment successfuly deleated", HttpStatus.OK);
    }


}
