package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.reservation;

@Repository
public interface ReservationRepo extends JpaRepository<reservation, Integer> {
    
        // @Query("SELECT a, b FROM service a JOIN reservation b ON a.service_id = b.service_id")
        // Optional<reservation> findByService_ServiceName(String serviceName);

        
}

