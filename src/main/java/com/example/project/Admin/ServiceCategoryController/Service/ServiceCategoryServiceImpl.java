package com.example.project.Admin.ServiceCategoryController.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;
import com.example.project.Admin.ServiceCategoryController.Repository.ServiceCategoryRepository;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;
    
    @Override
    public List<ServiceCategory> findAll() {
        return serviceCategoryRepository.findAll();
    }
    
}
