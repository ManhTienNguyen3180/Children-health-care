package com.example.project.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.project.Repository.UserRepository;
import com.example.project.entity.user;


@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<user> u = userRepository.findByEmail(username);
        user user = u.get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }else {
            
            return new CustomUser(user);
        }
    }    

    
}