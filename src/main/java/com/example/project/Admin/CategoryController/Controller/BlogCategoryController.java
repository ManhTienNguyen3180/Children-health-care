package com.example.project.Admin.CategoryController.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.project.Admin.CategoryController.Service.BlogCategoryService;

@Controller
public class BlogCategoryController {

    @Autowired
    BlogCategoryService blogCategoryService;
}