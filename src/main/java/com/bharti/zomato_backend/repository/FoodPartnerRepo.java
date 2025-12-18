package com.bharti.zomato_backend.repository;

import com.bharti.zomato_backend.entity.FoodPartner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodPartnerRepo extends JpaRepository<FoodPartner, Integer> {
    Optional<FoodPartner> findByEmail(String email);
}
