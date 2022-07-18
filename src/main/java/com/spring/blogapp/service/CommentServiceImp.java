package com.spring.blogapp.service;

import com.spring.blogapp.dto.CommentDto;
import com.spring.blogapp.entity.Comment;
import com.spring.blogapp.entity.Publication;
import com.spring.blogapp.exceptions.BlogAppException;
import com.spring.blogapp.exceptions.ResourceNotFoundException;
import com.spring.blogapp.repository.CommentRepository;
import com.spring.blogapp.repository.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private ModelMapper modelMapper;
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

    @Override
    public List<CommentDto> getCommentByPublicationID(Long publicationId) {
        List<Comment> comments = commentRepository.findByPublicationId(publicationId);
        return comments.stream().map(comment -> mapDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long publicationId, Long commentId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));

        //once found the publication we find the comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPublication().getId().equals(publication.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }
        return mapDTO(comment);
    }

    @Override
    public CommentDto modifyComment(Long publicationId, Long commentId, CommentDto commentRequest) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));

        //once found the publication we find the comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPublication().getId().equals(publication.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment modifiedComment = commentRepository.save(comment);

        return mapDTO(modifiedComment);
    }

    @Override
    public void deleteComment(Long publicationId, Long commentId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));

        //once found the publication we find the comment
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPublication().getId().equals(publication.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }

        commentRepository.delete(comment);
    }

    // Entity to DTO
    private CommentDto mapDTO(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    // DTO to Entity
    private Comment mapEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}

