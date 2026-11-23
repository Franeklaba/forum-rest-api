package com.example.project_spring.comment.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record CommentCollectionDto(
        UUID commentId,
        String authorName
) implements Comparable<CommentCollectionDto> {
    @Override
    public int compareTo(CommentCollectionDto o) {
        return this.authorName.compareTo(o.authorName);
    }
}
