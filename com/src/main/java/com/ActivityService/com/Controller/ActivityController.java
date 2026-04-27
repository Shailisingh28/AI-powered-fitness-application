package com.ActivityService.com.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ActivityService.com.DTO.ActivityRequest;
import com.ActivityService.com.DTO.ActivityResponse;
import com.ActivityService.com.Service.ActivityService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {
    ActivityService service;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request) {
        System.out.println("🔥 Controller Hit");
        return ResponseEntity.ok(service.trackActivity(request));
    }
}
