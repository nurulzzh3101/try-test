package com.example.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test.dto.UserSubscriptionDTO;
import com.example.test.services.services.UserSubscriptionService;

@RestController
@RequestMapping("/user/subscription")
public class UserSubscriptionController {
    @Autowired
    public  UserSubscriptionService userSubscriptionService;

    @PostMapping("/add")
    public ResponseEntity<Void> addSubscription(@RequestBody UserSubscriptionDTO userSubscriptionDTO){
        userSubscriptionService.addSubscription(userSubscriptionDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/udpate")
    public ResponseEntity<Void> updateSubscription(@RequestBody UserSubscriptionDTO userSubscriptionDTO){
        userSubscriptionService.updateSubscription(userSubscriptionDTO);
        return ResponseEntity.ok().build();
    }


}
