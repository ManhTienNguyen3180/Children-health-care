package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ReviewBlogRepo;
import com.example.project.entity.review_blog;

@Service
public class ReviewBlogService {
    @Autowired
    private ReviewBlogRepo repo;

    public void save(review_blog review_blog){
        repo.save(review_blog);
     }
}
