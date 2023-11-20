package com.example.test.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserSubscriptionDTO {
    public String id;
    public String name;
    public String type;
    public BigDecimal price;
    public String day;
    public String time;
    public int numOfMeetings;
    public String descAndDuration;
}
