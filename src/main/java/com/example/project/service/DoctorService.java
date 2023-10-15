package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.project.entity.doctor;


public interface DoctorService {
    List<doctor> fetchDoctorList();

    Optional<doctor> findDoctorById(int id);

    Page<doctor> getAllDoc(int page, int size);

    Page<doctor> getDocByService(int id,int page,int size);
    
    List<Object> getSerByDoc(int id);

    List<Object> getSlotByDoc(int id);

    List<Object> getDocReview(int id);
}