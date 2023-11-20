package com.example.test.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.test.entities.UserSubscription;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, String>{
    UserSubscription findSubscriptionsById(String id);
    UserSubscription findSubscriptionsByName(String name);
    UserSubscription findSubscriptionsByType(String type);
    
}
