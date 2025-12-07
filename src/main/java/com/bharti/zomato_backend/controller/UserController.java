package com.bharti.zomato_backend.controller;

import com.bharti.zomato_backend.dto.ApiResponse;
import com.bharti.zomato_backend.dto.UserDto;
import com.bharti.zomato_backend.service.UserService;
import com.bharti.zomato_backend.service.imp.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto user) {

        // check, is user already exists.
        if(userService.isUserAlreadyExist(user.getEmail())) {
            ApiResponse response = new ApiResponse("User already exists with email: "+user.getEmail(), false);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
        }

        UserDto newUser = userService.createUser(user);
        newUser.setPassword("********");
        return new ResponseEntity<>(newUser, HttpStatus.valueOf(201));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        // Check, user exists or not with email id
        if(!userService.isUserAlreadyExist(user.getEmail())) {
            ApiResponse response = new ApiResponse("Invalid email or password", false);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
        }
        // Authenticate user
        userService.authenticateUser(user);
        // After authentication, generate token
        String token = jwtService.generateToken(user.getEmail());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
