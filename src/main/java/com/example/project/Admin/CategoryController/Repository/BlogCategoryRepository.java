package com.example.project.Admin.CategoryController.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Admin.CategoryController.Model.BlogCategory;
@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory,Integer> {
    
}
