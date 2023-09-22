package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.doctor;


public interface DoctorService {
    List<doctor> fetchDoctorList();

    Optional<doctor> findDoctorById(int id);
}