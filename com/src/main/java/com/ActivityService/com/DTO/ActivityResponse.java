package com.ActivityService.com.DTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import com.ActivityService.com.Model.ActivityType;
import lombok.Data;

@Data
public class ActivityResponse {
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private HashMap<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
