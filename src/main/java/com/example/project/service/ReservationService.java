package com.example.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ReservationRepo;
import com.example.project.entity.reservation;
import com.example.project.entity.service;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo repo;

    public void save(reservation reservation) {
        repo.save(reservation);
    }


    // public Optional<reservation> findServiceByService_Name(String servicename) {
    //     return repo.findByService_ServiceName(servicename);
    // }


    public reservation findServiceByService_Name1(String servicename) {
        Optional<reservation> useOptional = repo.findByService_ServiceName(servicename);
        if (useOptional.isPresent()) {
            reservation p = useOptional.get();
            return p;
        }
        return null;
    }
}
