package com.example.project_spring.post.mapper;

import com.example.project_spring.post.dto.PostCollectionDto;
import com.example.project_spring.post.dto.PostCreateUpdateDto;
import com.example.project_spring.post.dto.PostDto;
import com.example.project_spring.post.entity.Post;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class PostMapper {

    public PostDto toDto(Post post) {
        return PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public PostCollectionDto toCollectionDto(Post post) {
        return PostCollectionDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .build();
    }

    public List<PostCollectionDto> toCollectionDtoList(Collection<Post> posts) {
        return posts.stream()
                .map(this::toCollectionDto)
                .toList();
    }

    public PostCreateUpdateDto toCreateUpdateDto(Post post) {
        return PostCreateUpdateDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public Post toEntity(PostCreateUpdateDto dto) {
        return Post.builder()
                .postId(UUID.randomUUID())
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    public Post toEntity(UUID postId, PostCreateUpdateDto dto) {
        return Post.builder()
                .postId(postId)
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    public Post toEntity(PostDto dto) {
        return Post.builder()
                .postId(dto.postId())
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    public Post toEntity(PostCollectionDto dto, String content) {
        return Post.builder()
                .postId(dto.postId())
                .title(dto.title())
                .content(content)
                .build();
    }

    public Post updateEntity(PostCreateUpdateDto dto, Post post) {
        post.setTitle(dto.title());
        post.setContent(dto.content());
        return post;
    }
}
