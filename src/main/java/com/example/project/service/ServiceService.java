package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ServiceRepo;
import com.example.project.entity.service;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepo repo;

    public List<service> fechServicesList(){
        return repo.findAll();
    }
    public Optional<service> findServiceById(int id){
        return repo.findById(id);
    }
}
