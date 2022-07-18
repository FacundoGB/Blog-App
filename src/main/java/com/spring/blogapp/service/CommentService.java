package com.spring.blogapp.service;

import com.spring.blogapp.dto.CommentDto;
import com.spring.blogapp.entity.Comment;

import java.util.List;

public interface CommentService {

    /*
        to create a comment we must parse the publication.
        but to find that publication we must first indicate its id (of the publication we will comment on)
        then we parse the comment we will publish
    */
    public CommentDto createComment(Long publicationId, CommentDto commentDto);

    public List<CommentDto> getCommentByPublicationID(Long publicationId);

    public CommentDto getCommentById(Long publicationId, Long commentId);

    public CommentDto modifyComment(Long publicationId, Long commentId, CommentDto commentRequest);

    public void deleteComment(Long publicationId, Long commentId);

}
