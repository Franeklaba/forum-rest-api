package com.example.project_spring.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record CommentCreateUpdateDto(
        String authorName,
        String content
) implements Comparable<CommentCreateUpdateDto> {
    @Override
    public int compareTo(CommentCreateUpdateDto o) {
        return this.authorName.compareTo(o.authorName);
    }
}
