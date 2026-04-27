package com.ActivityService.com.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ActivityService.com.Model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    public List<Activity> findByUserId(String userId);

}
