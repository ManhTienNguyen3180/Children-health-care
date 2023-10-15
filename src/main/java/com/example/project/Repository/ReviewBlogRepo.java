package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.review_blog;

@Repository
public interface ReviewBlogRepo extends JpaRepository<review_blog, Integer>{
    
}
