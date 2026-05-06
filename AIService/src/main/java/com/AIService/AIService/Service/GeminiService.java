package com.AIService.AIService.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiService {
    private final WebClient webClient;
    @Value("${gemini.api.key}")
    private String gemini_api_key;
    @Value("${gemini.api.url}")
    private String gemini_api_url;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        Map<String, Object> requestbody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", question)
                        })
                });
        String response = webClient.post().uri(gemini_api_key + gemini_api_url)
                .header("Content-Type", "application/json").bodyValue(requestbody).retrieve().bodyToMono(String.class)
                .block();
        return response;
    }
}
