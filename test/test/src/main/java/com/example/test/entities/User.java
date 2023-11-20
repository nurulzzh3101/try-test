package com.example.test.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "ms_user")
public class User{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String name;
    public String email;
    public String password;
    public String phone;
    public String status;
    public Timestamp createdDate;
    public Timestamp updatedDate;
    public String otp;
    public Timestamp otpCreated;
}