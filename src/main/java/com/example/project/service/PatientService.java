package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.project.Repository.PatientRepo;
import com.example.project.entity.patient;

@Service
public class PatientService {
    @Autowired
    PatientRepo repository;

    public void save(patient patient) {
        repository.save(patient);
    }

    //select last patient id from database by query mysql
    public int getLastPatientId() {
        return  repository.getLastPatientId();
    }

}
