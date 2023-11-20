package com.example.test.services.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dto.UserSubscriptionDTO;
import com.example.test.entities.UserSubscription;
import com.example.test.repositories.UserSubscriptionRepository;

import jakarta.transaction.Transactional;

@Service
public class UserSubscriptionService {
    @Autowired
    public UserSubscriptionRepository userSubscriptionRepository;

    
  @Transactional
    public void addSubscription(UserSubscriptionDTO userSubscriptionDTO){
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.name = userSubscriptionDTO.name;
        userSubscription.day = userSubscriptionDTO.day;
        userSubscription.descAndDuration = userSubscriptionDTO.descAndDuration;
        userSubscription.numOfMeetings = userSubscriptionDTO.numOfMeetings;
        userSubscription.price = userSubscriptionDTO.price;
        userSubscription.time = userSubscriptionDTO.time;
        userSubscription.type = userSubscriptionDTO.type;
        userSubscription.createdDate = new Timestamp(System.currentTimeMillis());
        userSubscriptionRepository.save(userSubscription);
    }

  @Transactional
    public void updateSubscription(UserSubscriptionDTO userSubscriptionDTO){
        UserSubscription userSubscription = userSubscriptionRepository.findSubscriptionsById(userSubscriptionDTO.id);
        userSubscription.name = userSubscriptionDTO.name;
        userSubscription.day = userSubscriptionDTO.day;
        userSubscription.descAndDuration = userSubscriptionDTO.descAndDuration;
        userSubscription.numOfMeetings = userSubscriptionDTO.numOfMeetings;
        userSubscription.price = userSubscriptionDTO.price;
        userSubscription.time = userSubscriptionDTO.time;
        userSubscription.type = userSubscriptionDTO.type;
        userSubscription.createdDate = new Timestamp(System.currentTimeMillis());
        userSubscriptionRepository.save(userSubscription);
    }


    public List<UserSubscription> getAll(){
        List<UserSubscription> subscription = new ArrayList<>();
        userSubscriptionRepository.findAll().forEach(subscription::add);
        return subscription;
    }

    public UserSubscription getByType(String type){
        UserSubscription subscription = userSubscriptionRepository.findSubscriptionsByType(type);
        return subscription;
    }

    public UserSubscription getByName(String name){
        UserSubscription subscription = userSubscriptionRepository.findSubscriptionsByName(name);
        return subscription;
    }

    public UserSubscription getById(String id){
        UserSubscription subscription = userSubscriptionRepository.findSubscriptionsById(id);
        return subscription;
    }

    public void deleteSubsription(String id){
        userSubscriptionRepository.deleteById(id);
    }
}
