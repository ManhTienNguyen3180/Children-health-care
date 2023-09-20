package com.example.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.user;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<user,Integer>{
    
    Optional<user> findByEmail(String email);
    
    Optional<user> findByResetToken(String resetToken);
    
}
