package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.blog;


public interface BlogService {
    List<blog> fetchBLogList();

    Optional<blog> findBlogById(int id);
}
