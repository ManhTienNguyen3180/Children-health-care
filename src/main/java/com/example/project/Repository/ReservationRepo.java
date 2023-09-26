package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.reservation;
import com.example.project.entity.service;

@Repository
public interface ReservationRepo extends JpaRepository<reservation, Integer> {
    
        

        
}

