package com.AIService.AIService.Model;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Activity {
    @Id
    private String id;
    private String userId;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private HashMap<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
