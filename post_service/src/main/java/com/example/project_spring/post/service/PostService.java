package com.example.project_spring.post.service;

import com.example.project_spring.post.entity.Post;
import com.example.project_spring.post.event.PostEventRepository;
import com.example.project_spring.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostEventRepository postEventRepository;

    public PostService(PostRepository postRepository, PostEventRepository postEventRepository) {
        this.postRepository = postRepository;
        this.postEventRepository = postEventRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id);
    }

    public Post save(Post post) {
        Post savedPost = postRepository.save(post);
        postEventRepository.sendCreateEvent(savedPost.getPostId());
        return savedPost;
    }

    public Post update(Post post) {
        return postRepository.save(post);
    }

    public void delete(UUID id) {
        postRepository.deleteById(id);
        postEventRepository.sendDeleteEvent(id);
    }
}
