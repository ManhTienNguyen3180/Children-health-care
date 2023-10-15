package com.example.project.Admin.BlogController.Service;

import java.util.List;
import java.util.Optional;

import com.example.project.Admin.BlogController.Model.Blog;

public interface BlogsService {

    List<Blog> findAll();

    void addBlog(int category, 
            String title, 
            String date, 
            String description,
            int status,
            String author,
            String image,
            String content,
            String update);

    Optional<Blog> getBlogByID(int id);
}
