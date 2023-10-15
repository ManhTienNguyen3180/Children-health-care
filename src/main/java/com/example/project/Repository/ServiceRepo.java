package com.example.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.service;

@Repository
public interface ServiceRepo extends JpaRepository<service, Integer>{
    
    @Query("select s from service s join doctorservice ds ON s.service_id = ds.serviceID where ds.doctorID = ?1")
    List<service> findServiceByDoctorID(int doctorID);
}
