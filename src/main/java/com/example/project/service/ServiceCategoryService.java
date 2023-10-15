package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ServiceCategoryRepo;
import com.example.project.entity.category_service;

@Service
public class ServiceCategoryService {
    @Autowired
    ServiceCategoryRepo repo;

    public List<category_service> fetchServiceCategoryList(){
        return repo.findAll();
    }
}
