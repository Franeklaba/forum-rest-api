package com.example.project_spring.post.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "posts")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Comparable<Post>, Serializable {
    @Id
    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Override
    public int compareTo(Post o) {
        return this.postId.compareTo(o.postId);
    }
}
