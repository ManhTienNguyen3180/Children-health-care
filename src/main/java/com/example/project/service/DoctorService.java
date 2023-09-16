package com.example.project.service;

import java.util.List;

import com.example.project.entity.doctor;


public interface DoctorService {
    List<doctor> fetchDoctorList();
}