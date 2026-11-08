package com.example.project_spring.cmd;

import com.example.project_spring.comment.entity.Comment;
import com.example.project_spring.comment.service.CommentService;
import com.example.project_spring.post.entity.Post;
import com.example.project_spring.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import java.util.UUID;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final PostService postService;
    private final CommentService commentService;

    public DataInitializer(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Post post1 = Post.builder()
                .postId(UUID.randomUUID())
                .title("Architektura Usług Internetowych")
                .content("Wprowadzenie do Spring Boota i Spring Data JPA.")
                .build();

        postService.save(post1);

        Comment comment1 = Comment.builder()
                .commentId(UUID.randomUUID())
                .authorName("Jan Kowalski")
                .content("Bardzo pomocny materiał!")
                .commentsPost(post1)
                .build();

        Comment comment2 = Comment.builder()
                .commentId(UUID.randomUUID())
                .authorName("Anna Nowak")
                .content("Czekam na kolejne laboratoria.")
                .commentsPost(post1)
                .build();

        commentService.save(comment1);
        commentService.save(comment2);
    }
}
