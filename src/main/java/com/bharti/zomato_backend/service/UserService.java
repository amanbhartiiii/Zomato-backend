package com.bharti.zomato_backend.service;

import com.bharti.zomato_backend.dto.UserDto;

public interface UserService {
    UserDto register(UserDto userDto);
    boolean isUserAlreadyExist(String email);
    String verify(UserDto user);
}
