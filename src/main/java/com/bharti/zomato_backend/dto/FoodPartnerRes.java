package com.bharti.zomato_backend.dto;

public class FoodPartnerRes {
    private String token;
    private FoodPartnerDto foodPartner;

    public  FoodPartnerRes(String token, FoodPartnerDto foodPartner) {
        this.token = token;
        this.foodPartner = foodPartner;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public FoodPartnerDto getFoodPartner() {
        return foodPartner;
    }

    public void setFoodPartner(FoodPartnerDto foodPartner) {
        this.foodPartner = foodPartner;
    }
}
