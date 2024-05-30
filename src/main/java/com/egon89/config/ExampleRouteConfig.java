package com.egon89.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("example")
@Configuration
public class ExampleRouteConfig {

  @Bean("exampleRouteBean")
  public RouteLocator exampleRouteConfig(RouteLocatorBuilder builder) {
    System.out.println("example route config init");
    return builder.routes()
        .route(p -> p
            .path("/get")
            .filters(f -> f.addRequestHeader("x-custom-header", "hello-world"))
            .uri("http://httpbin.org:80"))
        .build();
  }
}
