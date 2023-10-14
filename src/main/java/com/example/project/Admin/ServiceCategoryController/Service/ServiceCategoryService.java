package com.example.project.Admin.ServiceCategoryController.Service;

import java.util.List;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;

public interface ServiceCategoryService {
    List<ServiceCategory> findAll();
}
