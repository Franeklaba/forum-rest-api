package com.example.project_spring.comment.service;

import com.example.project_spring.comment.entity.Comment;
import com.example.project_spring.comment.repository.CommentRepository;
import com.example.project_spring.post.entity.Post;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(UUID id) {
        return commentRepository.findById(id);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(UUID id) {
        commentRepository.deleteById(id);
    }
    public List<Comment> findByPost(Post post) {
        return commentRepository.findByCommentsPost(post);
    }
}
