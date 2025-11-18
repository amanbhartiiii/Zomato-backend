package com.bharti.zomato_backend.service.imp;

import com.bharti.zomato_backend.dto.UserDto;
import com.bharti.zomato_backend.entity.User;
import com.bharti.zomato_backend.repository.UserRepo;
import com.bharti.zomato_backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto register(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User newUser = userRepo.save(user);
        return mapper.map(newUser, UserDto.class);
    }

    @Override
    public boolean isUserAlreadyExist(String email) {
        User user = userRepo.findByEmail(email);
        return user != null;
    }


}
