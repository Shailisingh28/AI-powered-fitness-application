package com.ActivityService.com.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ActivityService.com.DTO.ActivityRequest;
import com.ActivityService.com.DTO.ActivityResponse;
import com.ActivityService.com.Model.Activity;
import com.ActivityService.com.Repository.ActivityRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {
    private final ActivityRepository repository;
    private final ValidateUser validate;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public ActivityResponse trackActivity(ActivityRequest request) {
        boolean isvaliduser = validate.validateUser(request.getUserId());
        if (!isvaliduser) {
            throw new RuntimeException("Invalid User");
        }
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStarTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = repository.save(activity);
        // publish the activity to rabbitmq for processing
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
        } catch (Exception e) {
            log.error("failed to publish the message to rabbitMq: ", e);
        }
        return mapToActivityResponse(savedActivity);
    }

    public ActivityResponse mapToActivityResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();

        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setCreatedAt(activity.getCreatedAt());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setStartTime(activity.getStartTime());
        response.setUpdatedAt(activity.getUpdatedAt());

        return response;
    }

    public List<ActivityResponse> getActivityList(String userId) {
        List<Activity> list = repository.findByUserId(userId);
        return list.stream().map(this::mapToActivityResponse).collect(Collectors.toList());
    }

    public ActivityResponse getActivityById(String activityId) {
        return repository.findById(activityId).map(this::mapToActivityResponse)
                .orElseThrow(() -> new RuntimeException("Activity Not Found"));
    }
}
