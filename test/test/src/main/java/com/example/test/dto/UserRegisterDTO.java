package com.example.test.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class UserRegisterDTO {
    public String name;
    public String email;
    public String password;
    public String phone;
    public String cardName;
    public String cardId;
    public String cvv;
    public LocalDateTime expiredDate;
}
