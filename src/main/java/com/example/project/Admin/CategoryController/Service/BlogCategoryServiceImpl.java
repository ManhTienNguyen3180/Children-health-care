package com.example.project.Admin.CategoryController.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Admin.CategoryController.Model.BlogCategory;
import com.example.project.Admin.CategoryController.Repository.BlogCategoryRepository;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    BlogCategoryRepository blogCategoryRepository;

    @Override
    public List<BlogCategory> findAll() {
        return blogCategoryRepository.findAll();
    }
    
}
