package com.bharti.zomato_backend.service.imp;

import com.bharti.zomato_backend.dto.UserDto;
import com.bharti.zomato_backend.entity.User;
import com.bharti.zomato_backend.repository.UserRepo;
import com.bharti.zomato_backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManger;

    @Autowired
    private JWTService jwtService;

    @Override
    public UserDto createUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        User user = mapper.map(userDto, User.class);
        User newUser = userRepo.save(user);
        return mapper.map(newUser, UserDto.class);
    }

    @Override
    public boolean isUserAlreadyExist(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return user != null;
    }

    @Override
    public String loginUser(UserDto user) throws BadCredentialsException {
        try {
            Authentication authentication = authManger.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );
            // Step 2: Set authentication in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Step 3: Generate JWT token
            return jwtService.generateToken(user.getEmail());
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid email or password!");
        }
    }
}
