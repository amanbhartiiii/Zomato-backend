package com.bharti.zomato_backend.service;

import com.bharti.zomato_backend.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    boolean isUserAlreadyExist(String email);
    String loginUser(UserDto user);
}
