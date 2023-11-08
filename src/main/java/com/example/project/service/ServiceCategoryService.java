package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ServiceCategoryRepo;
import com.example.project.entity.category_service;

@Service
public class ServiceCategoryService {

    @Autowired
    ServiceCategoryRepo ServiceCategoryRepo;

    public List<category_service>  fetchServiceCategoryList() {
        return (List<category_service>) ServiceCategoryRepo.findAllCategoryService();
    }
    
    public Optional<category_service> findByID(int id) {
        return ServiceCategoryRepo.findById(id);
    }
}
