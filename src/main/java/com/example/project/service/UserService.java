package com.example.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.UserRepo;
import com.example.project.entity.user;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    public Optional<user> findByEmail(String email){
        return repo.findByEmail(email);
    }
    public Optional<user> findByResetToken(String resetToken){
        return repo.findByResetToken(resetToken);
    }
    public void save(user user){
        repo.save(user);
    }
    
}
