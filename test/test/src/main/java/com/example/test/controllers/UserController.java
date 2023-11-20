package com.example.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test.dto.UserRegisterDTO;
import com.example.test.entities.User;
import com.example.test.services.services.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserRegisterDTO userRegisterDTO) throws MessagingException{
        String response = userService.userRegister(userRegisterDTO);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}") 
    public ResponseEntity<User> getUserById(@PathVariable String id){
        User user = userService.getUserById(id);
        return new ResponseEntity<User>(user, null, 0);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUserById(@RequestBody String userId, String name, String email, String password, String phone){
        userService.updateUserById(userId, name, email, password, phone);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change/password")
    public ResponseEntity<Void> changeUserPasswordById(@RequestBody String userId, String email, String password){
        userService.changeUserPasswordById(userId, email, password);
        return ResponseEntity.ok().build();
    }
}
