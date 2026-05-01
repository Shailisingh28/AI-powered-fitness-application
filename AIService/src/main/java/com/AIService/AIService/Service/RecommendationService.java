package com.AIService.AIService.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.AIService.AIService.Model.Recommendation;
import com.AIService.AIService.Repository.Recommendationrepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final Recommendationrepo recommendationrepo;

    public List<Recommendation> getRecommendations(String userId) {
        return recommendationrepo.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityID) {
        return recommendationrepo.findByActivityId(activityID)
                .orElseThrow(() -> new RuntimeException(
                        "No recommendation found against this activity with id " + activityID));
    }
}
