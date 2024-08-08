package com.example.booking.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient customerWebClient() {
        return webClientBuilder()
                .baseUrl("http://customer-service/api/v1").build();
    }
    @Bean
    public WebClient roomWebClient() {
        return webClientBuilder()
                .baseUrl("http://room-service/api/v1").build();
    }

}