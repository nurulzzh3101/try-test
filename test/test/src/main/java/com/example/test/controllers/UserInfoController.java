package com.example.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test.entities.UserInfo;
import com.example.test.services.services.UserInfoService;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Autowired
    public UserInfoService userInfoService;

    @PostMapping("/add")
    public ResponseEntity<Void> addUserInfo(@RequestBody String userId, String userSubscriptionId, String status, int remainingAmount){
        userInfoService.addUserInfo(userId, userSubscriptionId, status, remainingAmount);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUserInfo(@RequestBody String userId, String userSubscriptionId, String status, int remainingAmount){
        userInfoService.updateUserInfo(userId, userSubscriptionId, status, remainingAmount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserInfo>> getAll(){
        List<UserInfo> userInfos = userInfoService.getAll();
        return new ResponseEntity<List<UserInfo>>(userInfos, null, 0);
    }

    @GetMapping("/get/id/user/{userId}")
    public ResponseEntity<List<UserInfo>> getByUserId(@PathVariable String userId){
        List<UserInfo> userInfos = userInfoService.getByUserId(userId);
        return new ResponseEntity<List<UserInfo>>(userInfos, null, 0);
    }

    @GetMapping("/get/id/subscription/{subscriptionId}")
    public ResponseEntity<List<UserInfo>> getBySubscriptionId(@PathVariable String subscriptionId){
        List<UserInfo> userInfos = userInfoService.getBySubscriptionId(subscriptionId);
        return new ResponseEntity<List<UserInfo>>(userInfos, null, 0);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserInfo> getById(@PathVariable String id){
        UserInfo userInfos = userInfoService.getById(id);
        return new ResponseEntity<UserInfo>(userInfos, null, 0);
    }

    @GetMapping("/get/status/{status}")
    public ResponseEntity<List<UserInfo>> getUserInfoByStatus(@PathVariable String status){
        List<UserInfo> userInfos = userInfoService.getUserByStatus(status);
        return new ResponseEntity<List<UserInfo>>(userInfos, null, 0);
    }

    @GetMapping("/get/amount/{amount}")
    public ResponseEntity<List<UserInfo>> getByRemainingAmount(@PathVariable int amount){
        List<UserInfo> userInfos = userInfoService.getByRemainingAmount(amount);
        return new ResponseEntity<List<UserInfo>>(userInfos, null, 0);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable String id){
        userInfoService.deleteUserInfo(id);
        return ResponseEntity.ok().build();
    }
    
}
