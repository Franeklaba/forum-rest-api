package com.example.project_spring.comment.mapper;

import com.example.project_spring.comment.dto.CommentCollectionDto;
import com.example.project_spring.comment.dto.CommentCreateUpdateDto;
import com.example.project_spring.comment.dto.CommentDto;
import com.example.project_spring.comment.entity.Comment;
import com.example.project_spring.post.entity.Post;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class CommentMapper {

    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .authorName(comment.getAuthorName())
                .content(comment.getContent())
                .build();
    }

    public CommentCollectionDto toCollectionDto(Comment comment) {
        return CommentCollectionDto.builder()
                .commentId(comment.getCommentId())
                .authorName(comment.getAuthorName())
                .build();
    }

    public List<CommentCollectionDto> toCollectionDtoList(Collection<Comment> comments) {
        return comments.stream()
                .map(this::toCollectionDto)
                .toList();
    }

    public CommentCreateUpdateDto toCreateUpdateDto(Comment comment) {
        return CommentCreateUpdateDto.builder()
                .authorName(comment.getAuthorName())
                .content(comment.getContent())
                .build();
    }

    public Comment toEntity(CommentCreateUpdateDto dto, Post post) {
        return Comment.builder()
                .commentId(UUID.randomUUID())
                .authorName(dto.authorName())
                .content(dto.content())
                .commentsPost(post)
                .build();
    }

    public Comment toEntity(UUID commentId, CommentCreateUpdateDto dto, Post post) {
        return Comment.builder()
                .commentId(commentId)
                .authorName(dto.authorName())
                .content(dto.content())
                .commentsPost(post)
                .build();
    }

    public Comment toEntity(CommentDto dto, Post post) {
        return Comment.builder()
                .commentId(dto.commentId())
                .authorName(dto.authorName())
                .content(dto.content())
                .commentsPost(post)
                .build();
    }

    public Comment toEntity(CommentCollectionDto dto, String content, Post post) {
        return Comment.builder()
                .commentId(dto.commentId())
                .authorName(dto.authorName())
                .content(content)
                .commentsPost(post)
                .build();
    }

    public Comment updateEntity(CommentCreateUpdateDto dto, Comment comment) {
        comment.setAuthorName(dto.authorName());
        comment.setContent(dto.content());
        return comment;
    }
}
