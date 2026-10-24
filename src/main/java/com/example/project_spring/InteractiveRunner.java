package com.example.project_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class InteractiveRunner implements CommandLineRunner {

    private final PostService postService;
    private final CommentService commentService;

    public InteractiveRunner(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n--- Interaktywne CLI uruchomione ---");
        System.out.println("Wpisz 'help' aby zobaczyć listę dostępnych komend.");

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "help":
                    System.out.println("Dostępne komendy: help, categories, elements, add, delete, quit");
                    break;

                case "categories":
                    System.out.println("--- Lista Kategorii (Postów) ---");
                    postService.findAll().forEach(post ->
                            System.out.println(post.getPostId() + " | " + post.getTitle()));
                    break;

                case "elements":
                    System.out.println("--- Lista Elementów (Komentarzy) ---");
                    commentService.findAll().forEach(comment ->
                            System.out.println(comment.getCommentId() + " | " + comment.getAuthorName() + " | W poście: " +
                                    (comment.getCommentsPost() != null ? comment.getCommentsPost().getPostId() : "brak")));
                    break;

                case "add":
                    try {
                        System.out.print("Podaj UUID kategorii (Posta): ");
                        UUID postId = UUID.fromString(scanner.nextLine().trim());

                        Post post = postService.findById(postId).orElse(null);
                        if (post == null) {
                            System.out.println("Błąd: Nie znaleziono posta o podanym ID.");
                            break;
                        }

                        System.out.print("Podaj autora komentarza: ");
                        String author = scanner.nextLine().trim();
                        System.out.print("Podaj treść komentarza: ");
                        String content = scanner.nextLine().trim();

                        Comment newComment = Comment.builder()
                                .commentId(UUID.randomUUID()) // Id generowane przez klienta (wymóg z pkt 1)
                                .authorName(author)
                                .content(content)
                                .commentsPost(post)
                                .build();

                        commentService.save(newComment);
                        System.out.println("Sukces: Dodano nowy element.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Błąd: Nieprawidłowy format UUID.");
                    }
                    break;

                case "delete":
                    try {
                        System.out.print("Podaj UUID elementu (Komentarza) do usunięcia: ");
                        UUID commentId = UUID.fromString(scanner.nextLine().trim());

                        commentService.delete(commentId);
                        System.out.println("Sukces: Usunięto element (lub nie istniał).");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Błąd: Nieprawidłowy format UUID.");
                    }
                    break;

                case "quit":
                    System.out.println("Zamykanie aplikacji...");
                    running = false;
                    System.exit(0); // Zabija aplikację, zamykając w tle serwer Tomcat
                    break;

                default:
                    System.out.println("Nieznana komenda. Wpisz 'help'.");
            }
        }
    }
}