package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.category_service;

@Repository
public interface ServiceCategoryRepo extends JpaRepository<category_service,Integer> {
    
}

