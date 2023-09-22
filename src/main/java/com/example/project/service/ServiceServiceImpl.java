package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ServiceRepo;
import com.example.project.entity.service;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepo repo;

    @Override
    public List<service> fetchServiceList() {
        return (List<service>) repo.findAll();
    }
    
}
