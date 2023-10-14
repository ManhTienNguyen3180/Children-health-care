package com.example.project.Admin.ServiceCategoryController.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;
@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory,Integer> {
    
}
