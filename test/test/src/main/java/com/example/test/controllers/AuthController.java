package com.example.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.test.dto.UserLoginDTO;
import com.example.test.dto.UserLogoutDTO;
import com.example.test.services.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserLoginDTO userLoginDTO){
        String response = authService.login(userLoginDTO);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> userLogout(@RequestBody UserLogoutDTO userLogoutDTO){
        String response = authService.logout(userLogoutDTO);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    
}
