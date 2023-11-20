package com.example.test.services.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.entities.UserActivity;
import com.example.test.repositories.UserActivityRepository;

import jakarta.transaction.Transactional;

@Service
public class UserActivityService {
    @Autowired
    public UserActivityRepository userActivityRepository;


  @Transactional
    public void addActivity(String userId, String type, String desc){
        UserActivity userActivity = new UserActivity();
        userActivity.userId = userId;
        userActivity.type = type;
        userActivity.description = desc;
        userActivity.createdDate = new Timestamp(System.currentTimeMillis());
        userActivityRepository.save(userActivity);
    }

    public List<UserActivity> findByUserId(String userId){
        List<UserActivity> activity = new ArrayList<>();
        userActivityRepository.findActivityByUserId(userId).forEach(activity::add);
        return activity;
    }

    public List<UserActivity> findByType(String type){
        List<UserActivity> activity = new ArrayList<>();
        userActivityRepository.findActivityByType(type).forEach(activity::add);
        return activity;
    }
}
