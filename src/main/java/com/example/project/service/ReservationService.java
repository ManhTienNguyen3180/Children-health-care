package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ReservationRepo;
import com.example.project.entity.reservation;

@Service
public class ReservationService {
    
    @Autowired
    ReservationRepo repository;

    public void save(reservation reservation) {
        repository.save(reservation);
    }

    
    
}
