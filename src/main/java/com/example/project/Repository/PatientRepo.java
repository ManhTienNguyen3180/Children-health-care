package com.example.project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.patient;

@Repository
public interface PatientRepo extends JpaRepository<patient, Integer>{
    @Query(value = "SELECT MAX(patient_id) FROM patient", nativeQuery = true)
    int getLastPatientId();
    
}