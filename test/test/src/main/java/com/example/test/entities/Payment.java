package com.example.test.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "ms_payment")
public class Payment {
     @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public BigDecimal amount;
    public String userId;
    public Timestamp createdDate;
    public Timestamp updatedDate;
    public String otp;
    public Timestamp otpCreated;
}
