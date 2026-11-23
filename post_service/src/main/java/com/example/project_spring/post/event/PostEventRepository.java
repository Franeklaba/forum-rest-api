package com.example.project_spring.post.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Repository
public class PostEventRepository {

    private final RestClient restClient;

    public PostEventRepository(@Value("${comment.service.url}") String commentServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(commentServiceUrl)
                .build();
    }

    public void sendCreateEvent(UUID postId) {
        restClient.post()
                .uri("/api/posts/{postId}", postId)
                .retrieve()
                .toBodilessEntity();
    }

    public void sendDeleteEvent(UUID postId) {
        restClient.delete()
                .uri("/api/posts/{postId}", postId)
                .retrieve()
                .toBodilessEntity();
    }
}
