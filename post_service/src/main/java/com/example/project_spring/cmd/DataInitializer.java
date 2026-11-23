package com.example.project_spring.cmd;

import com.example.project_spring.post.entity.Post;
import com.example.project_spring.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import java.util.UUID;

@Component
@Order(1)
@Profile("init")
public class DataInitializer implements CommandLineRunner {

    private final PostService postService;

    public DataInitializer(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {
        Post post1 = Post.builder()
                .postId(UUID.fromString("a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d"))
                .title("Architektura Usług Internetowych")
                .content("Wprowadzenie do Spring Boota i Spring Data JPA.")
                .build();

        postService.update(post1);
    }
}
