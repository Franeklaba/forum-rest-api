package com.example.project_spring.post.dto;

import lombok.Builder;

@Builder
public record PostCreateUpdateDto(
        String title,
        String content
) implements Comparable<PostCreateUpdateDto> {
    @Override
    public int compareTo(PostCreateUpdateDto o) {
        return this.title.compareTo(o.title);
    }
}
