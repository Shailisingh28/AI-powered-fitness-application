package com.AIService.AIService.Service;

import org.springframework.stereotype.Service;

import com.AIService.AIService.Model.Activity;
import com.AIService.AIService.Model.Recommendation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAIService {
    private final GeminiService geminiService;

    public String generateRecommendation(Activity activity) {
        String prompt = createpromptforActivity(activity);
        String aiResponse = geminiService.getAnswer(prompt);
        log.info("Response from AI {}", aiResponse);
        return aiResponse;
    }

    public String createpromptforActivity(Activity activity) {
        return """
                You are an expert AI assistant that analyzes user activities and generates meaningful recommendations.

                Your task is to analyze the given activity and generate a structured recommendation.

                IMPORTANT:
                - Respond ONLY in valid JSON format
                - Do NOT include any explanation or extra text
                - Follow the exact JSON structure below

                JSON FORMAT:
                {
                  "recommendation": "string",
                  "improvements": ["string", "string"],
                  "suggestions": ["string", "string"],
                  "safety": ["string", "string"]
                }

                ACTIVITY DETAILS:
                - Activity ID: %s
                - User ID: %s
                - Activity Type: %s
                - Description: %s
                - Duration: %s
                - Additional Info: %s

                GUIDELINES:
                - recommendation: Give a concise summary of performance
                - improvements: List actionable improvements
                - suggestions: Give smart tips or optimizations
                - safety: Mention any safety precautions if applicable

                Now generate the response.
                """.formatted(
                activity.getType(),
                activity.getDuration(),
                activity.getCaloriesBurned(),
                activity.getAdditionalMetrics());
    }
}
