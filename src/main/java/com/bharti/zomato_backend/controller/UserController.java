package com.bharti.zomato_backend.controller;

import com.bharti.zomato_backend.dto.ApiResponse;
import com.bharti.zomato_backend.dto.UserDto;
import com.bharti.zomato_backend.service.UserService;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto user) {

        // check, is user already exists.
        String email = user.getEmail();
        if(userService.isUserAlreadyExist(email)) {
            ApiResponse response = new ApiResponse("User already exists with email: "+email, false);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
        }

        UserDto newUser = userService.register(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        String token = userService.verify(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
