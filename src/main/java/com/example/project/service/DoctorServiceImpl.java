package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Page<doctor> getAllDoc(int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return repo.findAll( pageable);
    }

    @Override
    public Page<doctor> getDocByService(int id, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return repo.getDocByService(id, pageable);
    }

    @Override
    public List<Object> getSerByDoc(int id) {
        return repo.getServiceByDoc(id);
    }

    @Override
    public List<Object> getSlotByDoc(int id) {
        return repo.getSlotByDoc(id);
    }

    @Override
    public List<Object> getDocReview(int id) {
        return repo.getDocReview(id);
    }

    
    
}
