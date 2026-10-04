package com.example.implementingspringsecurity.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.implementingspringsecurity.DTO.UserRequestDto;
import com.example.implementingspringsecurity.Services.AuthService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController

public class AuthController {

    private final AuthService authService;

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto)
    {
        String message =  authService.registerUser(userRequestDto);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto userRequestDto)
    {
        String token =  authService.loginUser(userRequestDto);
        return new ResponseEntity<>(token,HttpStatus.ACCEPTED);
    }

}
