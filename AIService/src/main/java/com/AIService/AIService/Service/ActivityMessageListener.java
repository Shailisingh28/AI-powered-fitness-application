package com.AIService.AIService.Service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.AIService.AIService.Model.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityMessageListener {
    private ActivityAIService service;

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {
        System.out.println("🔥 MESSAGE RECEIVED 🔥");
        log.info("Received Activity for processing: {}", activity.getId());
        log.info("Generated Recommendation: {}", service.generateRecommendation(activity));
    }
}
