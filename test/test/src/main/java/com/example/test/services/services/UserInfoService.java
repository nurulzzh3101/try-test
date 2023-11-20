package com.example.test.services.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.entities.UserInfo;
import com.example.test.repositories.UserInfoRepository;

import jakarta.transaction.Transactional;

@Service
public class UserInfoService {
    @Autowired
    public UserInfoRepository userInfoRepository;
    
    
     @Transactional
    public void addUserInfo (String userId, String userSubscriptionId, String status, int remainingAmount){
        UserInfo userInfo = new UserInfo();
        userInfo.userId = userId;
        userInfo.userSubscriptionId = userSubscriptionId;
        userInfo.remainingAmount = remainingAmount;
        userInfo.createdDate = new Timestamp(System.currentTimeMillis());
        userInfo.status = status;
        userInfoRepository.save(userInfo);
    }
    
    
    @Transactional
    public void updateUserInfo(String userId, String userSubscriptionId, String status, int remainingAmount){
        UserInfo userInfo = userInfoRepository.findUserInfoById(userId);
        userInfo.updatedDate = new Timestamp(System.currentTimeMillis());
        userInfo.userSubscriptionId = userSubscriptionId;
        userInfo.status = status;
        userInfo.remainingAmount = remainingAmount;
        userInfoRepository.save(userInfo);
    }

    public List<UserInfo> getAll(){
        List<UserInfo> info = new ArrayList<>();
        userInfoRepository.findAll().forEach(info::add);
        return info;
    }

    public List<UserInfo> getByUserId(String userId){
        List<UserInfo> info = new ArrayList<>();
        userInfoRepository.findUserInfoByUserId(userId).forEach(info::add);
        return info;
    }

    public List<UserInfo> getBySubscriptionId(String useruserSubscriptionId){
        List<UserInfo> info = new ArrayList<>(); 
        userInfoRepository.findUserInfoBySubscriptionId(useruserSubscriptionId).forEach(info::add);
        return info;
    }

    public UserInfo getById(String id){
        UserInfo info = userInfoRepository.findUserInfoById(id);
        return info;
    }

    public List<UserInfo> getUserByStatus(String status){
        List<UserInfo> info = new ArrayList<>();
        userInfoRepository.findUserInfoByStatus(status).forEach(info::add);
        return info;
    }

    public List<UserInfo> getByRemainingAmount(int remainingAmount){
        List<UserInfo> info = new ArrayList<>();
        userInfoRepository.findUserInfoByRemainingAmount(remainingAmount).forEach(info::add);
        return info;
    }

    public String deleteUserInfo(String id){
        userInfoRepository.deleteById(id);
        return "successfull";
    }
}
