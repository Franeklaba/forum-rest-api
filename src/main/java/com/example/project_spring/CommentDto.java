package com.example.project_spring;

import lombok.Builder;
import java.util.UUID;

@Builder
public record CommentDto(
        UUID commentId,
        String authorName,
        String Content,
        String postTitle
) implements Comparable<CommentDto> {
    @Override
    public int compareTo(CommentDto o){
        return this.authorName.compareTo(o.authorName);
    }
}