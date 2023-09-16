package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.DoctorRepo;
import com.example.project.entity.doctor;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepo repo;

    @Override
    public List<doctor> fetchDoctorList() {
        return (List<doctor>) repo.findAll();
    }
    
}
