package com.example.project_spring.post.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record PostDto(
        UUID postId,
        String title,
        String content
) implements Comparable<PostDto> {
    @Override
    public int compareTo(PostDto o) {
        return this.title.compareTo(o.title);
    }
}
