package com.egon89.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRouteConfig {

  private final Logger logger = LoggerFactory.getLogger(LoadBalancedRouteConfig.class);

  @Bean
  public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {
    logger.info("load balanced route config");
    // lb means load balance
    return builder.routes()
        .route("beer-service", r -> r.path("/api/v1/beers*", "/api/v1/beers/*", "/api/v1/beers/upc/*")
            .uri("lb://beer-service"))
        .route("order-service", r -> r.path("/customers**", "/customers/**")
            .uri("lb://order-service"))
        .route("beer-inventory-service", r -> r.path("/beers/*/inventory")
            .uri("lb://beer-inventory-service"))
        .build();
  }
}
