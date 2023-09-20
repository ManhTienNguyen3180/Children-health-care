package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.blog;


public interface BlogService {

    //Read Blog List
    List<blog> fetchBLogList();

    //Get Blog by ID
    Optional<blog> findBlogById(int id);

    //Save blog
    void save(blog blog);

    //Detele blog
    void delete(int id);
}
