package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.BlogRepo;
import com.example.project.entity.blog;

@Service
public class BlogServiceImpl implements BlogService {
    
    @Autowired
    private BlogRepo repo;

    @Override
    public List<blog> fetchBLogList() {
        return (List<blog>) repo.findAll();
    }

    @Override
    public Optional<blog> findBlogById(int id) {
        return (Optional<blog>) repo.findById(id);
        
    }

    //Save Blog
    @Override
    public void save(blog blog) {
        repo.save(blog);
    }

    //Delete blog
    @Override
    public void delete(int id) {
        repo.deleteById(id);;
    }

    

    

    

   
    
}
