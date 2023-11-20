package com.example.test.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "ms_user_activity")
public class UserActivity{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    
    public String userId;
    public String type;
    public String description;
    public Timestamp createdDate;
    public Timestamp updatedDate;

}