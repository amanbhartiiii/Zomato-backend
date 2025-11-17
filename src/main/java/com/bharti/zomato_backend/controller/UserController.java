package com.bharti.zomato_backend.controller;

import com.bharti.zomato_backend.dto.UserDto;
import com.bharti.zomato_backend.entity.User;
import com.bharti.zomato_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto user) {
        UserDto newUser = userService.register(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
