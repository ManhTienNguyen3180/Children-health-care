package com.example.project.service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<doctor> findDoctorById(int id) {
        // TODO Auto-generated method stub
        return (Optional<doctor>) repo.findById(id);
    }
    
}
