package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.BlogCategoryRepo;
import com.example.project.entity.category_blog;
@Service
public class BlogCategoryService {

    @Autowired
    BlogCategoryRepo repo;

    public List<category_blog> fetchBLogCategoryList(){
        return repo.findAll();
    }
}
