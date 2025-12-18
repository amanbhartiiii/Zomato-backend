package com.bharti.zomato_backend.controller;

import com.bharti.zomato_backend.dto.FoodPartnerDto;
import com.bharti.zomato_backend.dto.FoodPartnerRes;
import com.bharti.zomato_backend.service.FoodPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/foodpartner")
public class FoodPartnerController {

    @Autowired
    private FoodPartnerService foodPartnerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerFoodPartner(@RequestBody FoodPartnerDto foodPartnerDto) {
        FoodPartnerDto newFoodPartner = foodPartnerService.createFoodPartner(foodPartnerDto);
        return new ResponseEntity<>(newFoodPartner, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity <?> loginFoodPartner(@RequestBody FoodPartnerDto foodPartnerDto) {
        String token = foodPartnerService.loginFoodPartner(foodPartnerDto);
        FoodPartnerDto foodPartner = foodPartnerService.getFoodPartner(foodPartnerDto.getEmail());
        FoodPartnerRes response = new FoodPartnerRes(token, foodPartner);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
