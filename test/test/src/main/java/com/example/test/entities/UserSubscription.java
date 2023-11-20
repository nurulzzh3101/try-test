package com.example.test.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import jakarta.persistence.Table;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "ms_user_subscription")
public class UserSubscription{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String name;
    public String type;
    public BigDecimal price;
    public String day;
    public String time;
    public int numOfMeetings;
    public String descAndDuration;
    public Timestamp createdDate;
    public Timestamp updatedDate;

}