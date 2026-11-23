package com.example.project_spring.post.controller;

import com.example.project_spring.post.entity.Post;
import com.example.project_spring.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Void> addPost(@PathVariable UUID postId) {
        Post post = Post.builder()
                .postId(postId)
                .build();
        postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId) {
        return postService.findById(postId)
                .<ResponseEntity<Void>>map(post -> {
                    postService.delete(post.getPostId());
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
