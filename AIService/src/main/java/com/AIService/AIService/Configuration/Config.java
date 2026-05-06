package com.AIService.AIService.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableMongoAuditing
public class Config {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
