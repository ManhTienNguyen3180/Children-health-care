package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.doctor;

@Repository
public interface DoctorRepo extends JpaRepository<doctor, Integer>{
    
}
