package com.example.project_spring.cmd;

import com.example.project_spring.comment.entity.Comment;
import com.example.project_spring.comment.service.CommentService;
import com.example.project_spring.post.entity.Post;
import com.example.project_spring.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
@Profile("cli")
public class InteractiveCLI implements CommandLineRunner {

    private final PostService postService;
    private final CommentService commentService;

    public InteractiveCLI(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Write 'help' to see available commands.");

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "help":
                    System.out.println("commands: help, categories, elements, add, delete, quit");
                    break;

                case "categories":
                    System.out.println("List of Categories (Posts)");
                    postService.findAll().forEach(post ->
                            System.out.println(post.getPostId() + " | " + post.getTitle() + " \n     " + post.getContent()));
                    break;

                case "elements":
                    System.out.println("List of Elements (Comments)");
                    commentService.findAll().forEach(comment ->
                            System.out.println(comment.getCommentId() + " | " + comment.getAuthorName() + " | In post: " +
                                    (comment.getCommentsPost() != null ? comment.getCommentsPost().getPostId() : "none") +
                                    "\n     " + comment.getContent()));
                    break;

                case "add":
                    try {
                        System.out.print("Enter category (Post) UUID: ");
                        UUID postId = UUID.fromString(scanner.nextLine().trim());

                        Post post = postService.findById(postId).orElse(null);
                        if (post == null) {
                            System.out.println("Error: Post with the given ID not found.");
                            break;
                        }

                        System.out.print("Enter comment author: ");
                        String author = scanner.nextLine().trim();
                        System.out.print("Enter comment content: ");
                        String content = scanner.nextLine().trim();

                        Comment newComment = Comment.builder()
                                .commentId(UUID.randomUUID())
                                .authorName(author)
                                .content(content)
                                .commentsPost(post)
                                .build();

                        commentService.save(newComment);
                        System.out.println("Success: New element added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid UUID format.");
                    }
                    break;

                case "delete":
                    try {
                        System.out.print("Enter element (Comment) UUID to delete: ");
                        UUID commentId = UUID.fromString(scanner.nextLine().trim());

                        commentService.delete(commentId);
                        System.out.println("Success: Element deleted (or it did not exist).");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid UUID format.");
                    }
                    break;

                case "quit":
                    System.out.println("Closing application...");
                    running = false;
                    System.exit(0);
                    break;

                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
