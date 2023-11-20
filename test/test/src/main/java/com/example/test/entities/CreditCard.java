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

@Table(name = "ms_credit_card")
public class CreditCard{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String userId;
    public String name;
    public String cardId;
    public String cvv;
    public Timestamp expiredDate;
    public Timestamp createdDate;
    public Timestamp updatedDate;
    

}