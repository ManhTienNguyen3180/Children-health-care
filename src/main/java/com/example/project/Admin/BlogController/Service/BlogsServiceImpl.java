package com.example.project.Admin.BlogController.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Admin.BlogController.Model.Blog;
import com.example.project.Admin.BlogController.Repository.BlogRepository;

@Service
public class BlogsServiceImpl implements BlogsService {

    @Autowired
    BlogRepository repo;

    // Return list of All Blog in Database
    @Override
    public List<Blog> findAll() {
        return repo.findAll();
    }

    // Save Blog, i must custom query, because my leader create a attribute
    // same as a ... of db
    @Override
    public void addBlog(int category, String title, String date, String description, int status, String author,
            String image, String content, String update) {
        repo.insertBlog(category, title, date, description, status, author, image, content, update);
    }

    @Override
    public Optional<Blog> getBlogByID(int id) {
        return repo.findById(id);
    }

}
