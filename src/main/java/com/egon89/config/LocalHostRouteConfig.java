package com.egon89.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local-discovery")
@Configuration
public class LocalHostRouteConfig {

  private final Logger logger = LoggerFactory.getLogger(LocalHostRouteConfig.class);

  @Bean("localHostRouteBean")
  public RouteLocator localHostRouteConfig(RouteLocatorBuilder builder) {
    logger.info("local host route config");
    return builder.routes()
        .route("beer-service", r -> r.path("/api/v1/beers*", "/api/v1/beers/*", "/api/v1/beers/upc/*")
            .uri("http://localhost:8080"))
        .route("order-service", r -> r.path("/customers**", "/customers/**")
            .uri("http://localhost:8081"))
        .route("beer-inventory-service", r -> r.path("/beers/*/inventory")
            .uri("http://localhost:8082"))
        .build();
  }
}
