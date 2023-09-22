package com.example.project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.patient;

@Repository
public interface PatientRepo extends JpaRepository<patient, Integer>{
    
}