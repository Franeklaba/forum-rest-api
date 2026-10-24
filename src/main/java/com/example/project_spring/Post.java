package com.example.project_spring;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "posts")
@ToString
@EqualsAndHashCode
@Getter
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "commentsPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @Override
    public int compareTo(Post o) {
        return this.postId.compareTo(o.postId);
    }
}