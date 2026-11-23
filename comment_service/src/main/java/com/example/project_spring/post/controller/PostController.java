package com.example.project_spring.post.controller;

import com.example.project_spring.post.dto.PostCreateUpdateDto;
import com.example.project_spring.post.dto.PostDto;
import com.example.project_spring.post.entity.Post;
import com.example.project_spring.post.mapper.PostMapper;
import com.example.project_spring.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostCreateUpdateDto newPostData) {
        Post savedPost = postService.save(postMapper.toEntity(newPostData));
        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toDto(savedPost));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID postId) {
        return postService.findById(postId)
                .map(post -> ResponseEntity.ok(postMapper.toDto(post)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID postId, @RequestBody PostCreateUpdateDto updatedPostData) {
        return postService.findById(postId)
                .map(post -> {
                    Post savedPost = postService.save(postMapper.updateEntity(updatedPostData, post));
                    return ResponseEntity.ok(postMapper.toDto(savedPost));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
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
