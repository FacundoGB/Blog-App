package com.spring.blogapp.service;

import com.spring.blogapp.dto.CommentDto;
import com.spring.blogapp.entity.Comment;
import com.spring.blogapp.entity.Publication;
import com.spring.blogapp.exceptions.ResourceNotFoundException;
import com.spring.blogapp.repository.CommentRepository;
import com.spring.blogapp.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public CommentDto createComment(Long publicationId, CommentDto commentDto) {
        Comment comment = mapEntity(commentDto);
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));

        comment.setPublication(publication);
        Comment newComment = commentRepository.save(comment);

        return mapDTO(newComment);
    }

    // Entity to DTO
    private CommentDto mapDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    // DTO to Entity
    private Comment mapEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }
}

