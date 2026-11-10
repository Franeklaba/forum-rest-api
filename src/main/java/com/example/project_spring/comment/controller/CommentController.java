package com.example.project_spring.comment.controller;

import com.example.project_spring.comment.entity.Comment;
import com.example.project_spring.comment.mapper.CommentMapper;
import com.example.project_spring.comment.service.CommentService;
import com.example.project_spring.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project_spring.comment.dto.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final CommentMapper commentMapper;
    public CommentController(CommentService commentService, PostService postService, CommentMapper commentMapper){
        this.commentService = commentService;
        this.postService = postService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable UUID postId, @RequestBody CommentCreateUpdateDto newCommentData){
        return postService.findById(postId).map(commentsPost -> ResponseEntity.status(HttpStatus.CREATED).
                body(commentMapper.toDto(commentService.save(commentMapper.toEntity(newCommentData, commentsPost)))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable UUID postId, @PathVariable UUID commentId){
        return commentService.findById(commentId)
                .filter(comment -> comment.getCommentsPost().getPostId().equals(postId))
                .map(comment -> ResponseEntity.ok(commentMapper.toDto(comment)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable UUID postId, @PathVariable UUID commentId,  @RequestBody CommentCreateUpdateDto updatedCommentData){
        return commentService.findById(commentId)
                .filter(comment -> comment.getCommentsPost().getPostId().equals(postId))
                .map(comment -> {
                    Comment savedComment = commentService.save(commentMapper.updateEntity(updatedCommentData, comment));
                    return ResponseEntity.ok(commentMapper.toDto(savedComment));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID postId, @PathVariable UUID commentId){
        return commentService.findById(commentId)
                .filter(comment -> comment.getCommentsPost().getPostId().equals(postId))
                .<ResponseEntity<Void>>map(comment -> {
                    commentService.delete(comment.getCommentId());
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
