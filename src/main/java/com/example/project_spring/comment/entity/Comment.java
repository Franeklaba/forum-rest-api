package com.example.project_spring.comment.entity;

import com.example.project_spring.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "comments")
@ToString
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Comparable<Comment>, Serializable {

    @Id
    @Column(name = "comment_id")
    private UUID commentId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "content")
    private String content;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post commentsPost;

    @Override
    public int compareTo(Comment o) {
        return this.commentId.compareTo(o.commentId);
    }
}
