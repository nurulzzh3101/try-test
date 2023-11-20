package com.example.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entities.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
    UserInfo findUserInfoById(String id);
    List<UserInfo> findUserInfoByUserId(String userId);
    List<UserInfo> findUserInfoBySubscriptionId(String userSubscriptionId);
    List<UserInfo> findUserInfoByStatus(String status);
    List<UserInfo> findUserInfoByRemainingAmount(int remainingAmount);
}
