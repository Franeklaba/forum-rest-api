package com.example.api_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${post.service.url}") String postServiceUrl,
			@Value("${comment.service.url}") String commentServiceUrl
	) {
		return builder
				.routes()
				.route("posts", route -> route
						.path(
								"/api/posts",
								"/api/posts/{postId}"
						)
						.uri(postServiceUrl)
				)
				.route("comments", route -> route
						.path(
								"/api/posts/{postId}/comments",
								"/api/posts/{postId}/comments/",
								"/api/posts/{postId}/comments/{commentId}"
						)
						.uri(commentServiceUrl)
				)
				.build();
	}

}
