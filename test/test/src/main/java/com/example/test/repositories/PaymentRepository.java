package com.example.test.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>{
    Optional<Payment> findById(String Id);
    List<Payment> findByUserId(String userId);
}
