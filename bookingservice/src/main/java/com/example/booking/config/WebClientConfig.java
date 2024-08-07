package com.example.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient customerWebClient() {
        return webClientBuilder()
                .baseUrl("http://localhost:8091/api/v1").build();
    }
    @Bean
    public WebClient roomWebClient() {
        return webClientBuilder()
                .baseUrl("http://localhost:8090/api/v1").build();
    }

}