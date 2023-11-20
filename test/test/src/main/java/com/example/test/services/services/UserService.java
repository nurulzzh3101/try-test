package com.example.test.services.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dto.UserRegisterDTO;
import com.example.test.entities.User;
import com.example.test.repositories.UserRepository;

import jakarta.mail.*;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public OTPService otpService;

    @Autowired
    public CreditCardService creditCardService;

    @Autowired
    public UserActivityService userActivityService;


    @Transactional
    public String userRegister(UserRegisterDTO userRegisterDTO) throws MessagingException {
        User checkUser = userRepository.findUserByEmail(userRegisterDTO.email);
        if (checkUser != null) {
            return "user already exist";
        }

        String otp = otpService.createOTP();

        try {
            otpService.emailOTP(userRegisterDTO.email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("cannot send otp");
        }

        User user = new User();
        user.name = userRegisterDTO.name;
        user.email = userRegisterDTO.email;
        user.password = userRegisterDTO.password;
        user.phone = userRegisterDTO.phone;
        user.createdDate = new Timestamp(System.currentTimeMillis());
        user.otp = otp;
        user.otpCreated = new Timestamp(System.currentTimeMillis());
        userRepository.save(user);

        userActivityService.addActivity(user.id, "REGISTER", "user register");
        creditCardService.addCreditCard(userRegisterDTO.cardId, userRegisterDTO.cardName, user.id, userRegisterDTO.cvv, userRegisterDTO.expiredDate);

        return "successfull";
    }

    public User getUserById(String userId){
        User user = userRepository.findUserById(userId);
        return user;
    }

    public void deleteUserById(String userId){
        userRepository.deleteById(userId);
    }

    
  @Transactional
    public void updateUserById(String userId, String name, String email, String password, String phone){
        User user = userRepository.findUserById(userId);
        user.email = email;
        user.password = password;
        user.name = name;
        user.phone = phone;
        userRepository.save(user);
    }

    
  @Transactional
    public void changeUserPasswordById(String userId, String email, String password){
        User user = userRepository.findUserById(userId);
        if (user.email == email){ 
            user.password = password;
            userRepository.save(user);
        }
    }

}
