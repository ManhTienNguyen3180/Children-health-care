package com.example.project.Admin.CategoryController.Service;

import java.util.List;

import com.example.project.Admin.CategoryController.Model.BlogCategory;

public interface BlogCategoryService {
    List<BlogCategory> findAll();
}
