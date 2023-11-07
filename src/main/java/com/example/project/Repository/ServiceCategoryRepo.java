package com.example.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.category_service;
import com.example.project.entity.service;

@Repository
public interface ServiceCategoryRepo extends JpaRepository<category_service,Integer> {
    @Query("select c from category_service c where c.status = 1")
    List<category_service> findAllCategoryService(); 
     


     

}

