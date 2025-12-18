package com.bharti.zomato_backend.service;

import com.bharti.zomato_backend.dto.FoodPartnerDto;
import org.springframework.security.authentication.BadCredentialsException;

public interface FoodPartnerService {
    FoodPartnerDto createFoodPartner(FoodPartnerDto foodPartnerDto);
    String loginFoodPartner(FoodPartnerDto foodPartnerDto) throws BadCredentialsException;
    FoodPartnerDto getFoodPartner(String email);
}
