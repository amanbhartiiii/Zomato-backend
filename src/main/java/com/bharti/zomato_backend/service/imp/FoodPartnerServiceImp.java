package com.bharti.zomato_backend.service.imp;

import com.bharti.zomato_backend.dto.FoodPartnerDto;
import com.bharti.zomato_backend.entity.FoodPartner;
import com.bharti.zomato_backend.repository.FoodPartnerRepo;
import com.bharti.zomato_backend.service.FoodPartnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FoodPartnerServiceImp implements FoodPartnerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FoodPartnerRepo foodPartnerRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public FoodPartnerDto createFoodPartner(FoodPartnerDto foodPartnerDto) {
        String encodedPassword = passwordEncoder.encode(foodPartnerDto.getPassword());
        foodPartnerDto.setPassword(encodedPassword);
        FoodPartner newFoodPartner = foodPartnerRepo.save(mapper.map(foodPartnerDto, FoodPartner.class));
        return mapper.map(newFoodPartner, FoodPartnerDto.class);
    }

    @Override
    public String loginFoodPartner(FoodPartnerDto foodPartnerDto) throws BadCredentialsException {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            foodPartnerDto.getEmail(),
                            foodPartnerDto.getPassword()
                    )
            );
            return jwtService.generateToken(foodPartnerDto.getEmail());
        } catch (BadCredentialsException ex) {
            System.out.println("Bad Credential");
            throw new BadCredentialsException("Invalid email or password!");

        }
    }

    public FoodPartnerDto getFoodPartner(String email) {
        FoodPartner foodPartner = foodPartnerRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Food partner not found"));
        return mapper.map(foodPartner, FoodPartnerDto.class);
    }
}
