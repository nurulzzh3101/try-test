package com.example.test.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dto.UserLoginDTO;
import com.example.test.dto.UserLogoutDTO;
import com.example.test.entities.User;
import com.example.test.repositories.UserActivityRepository;
import com.example.test.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
    @Autowired
    public UserActivityRepository userActivityRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserActivityService userActivityService;


    @Transactional
    public String login (UserLoginDTO userLoginDTO){
        User user = userRepository.findUserByEmail(userLoginDTO.email);
        if(user == null){
            return "user not found, please register";
        }

        if(!user.password.equals(userLoginDTO.password)){
            return "wrong password, please re-input";
        }

        if (!user.status.equals("ACTIVE")){
            return "your account id not active";
        }

        userActivityService.addActivity(user.id, "LOGIN", "user login");
        return "successfull";
    }

    @Transactional
    public String logout (UserLogoutDTO userLogoutDTO){
        User user = userRepository.findUserByEmail(userLogoutDTO.email);
        if(!user.id.equals(userLogoutDTO.userId)){
            return "something wrong when logout";
        }

        userActivityService.addActivity(user.id, "LOGOUT", "user logout");
        return "successfull";
    }
}
