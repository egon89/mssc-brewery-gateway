package com.egon89.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalHostRouteConfig {


  @Bean("localHostRouteBean")
  public RouteLocator localHostRouteConfig(RouteLocatorBuilder builder) {
    System.out.println("local host route config");
    return builder.routes()
        .route(r -> r.path("/api/v1/beers*", "/api/v1/beers/*", "/api/v1/beers/upc/*")
            .uri("http://localhost:8080"))
        .build();
  }
}