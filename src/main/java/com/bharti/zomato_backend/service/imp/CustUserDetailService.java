package com.bharti.zomato_backend.service.imp;

import com.bharti.zomato_backend.dto.UserPrincipal;
import com.bharti.zomato_backend.entity.User;
import com.bharti.zomato_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if(user == null) {
            System.out.println("User not found with Email: "+username);
            throw new UsernameNotFoundException("User not found with Email: "+username);
        }
        return new UserPrincipal(user);
    }
}
