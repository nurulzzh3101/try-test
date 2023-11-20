package com.example.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entities.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, String>{
    List<UserActivity> findActivityByUserId(String userId);
    List<UserActivity> findActivityByType(String type);
}
