package com.Rent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Rent.repository.UserRepository;
import com.Rent.models.User;

@Service
public class UserDetailsServiceImpl implements   UserDetailsService{

    @Autowired
    UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user= userRepo.findByEmail(email);
        if(user!=null)
        {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("User Not Available");
    }






}

