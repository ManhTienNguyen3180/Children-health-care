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


    // public reservation findServiceByService_Name1(String servicename) {
    //     Optional<reservation> useOptional = repository.findByService_ServiceName(servicename);
    //     if (useOptional.isPresent()) {
    //         reservation p = useOptional.get();
    //         return p;
    //     }
    //     return null;
    // }
}
