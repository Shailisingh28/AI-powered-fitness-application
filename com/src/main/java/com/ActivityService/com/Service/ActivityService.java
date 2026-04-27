package com.ActivityService.com.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ActivityService.com.DTO.ActivityRequest;
import com.ActivityService.com.DTO.ActivityResponse;
import com.ActivityService.com.Model.Activity;
import com.ActivityService.com.Repository.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository repository;

    public ActivityResponse trackActivity(ActivityRequest request) {

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStarTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = repository.save(activity);
        System.out.println("Saved Activity ID: " + savedActivity.getId());
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
}
