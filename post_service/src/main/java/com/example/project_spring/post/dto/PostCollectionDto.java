package com.example.project_spring.post.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record PostCollectionDto(
        UUID postId,
        String title
) implements Comparable<PostCollectionDto> {
    @Override
    public int compareTo(PostCollectionDto o) {
        return this.title.compareTo(o.title);
    }
}
