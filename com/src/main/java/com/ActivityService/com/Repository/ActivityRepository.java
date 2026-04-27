package com.ActivityService.com.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ActivityService.com.Model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

}
