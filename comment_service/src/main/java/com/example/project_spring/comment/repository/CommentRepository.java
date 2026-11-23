package com.example.project_spring.comment.repository;

import com.example.project_spring.comment.entity.Comment;
import com.example.project_spring.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByCommentsPost(Post post);
}
