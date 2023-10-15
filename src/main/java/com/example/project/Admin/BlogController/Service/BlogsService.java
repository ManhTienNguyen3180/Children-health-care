package com.example.project.Admin.BlogController.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

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

    Page<Blog> findPaginated(int pageNo, int pageSize);

    Page<Blog> searchbyTitleAuthorordescription(String key, int pageNo, int pageSize);

    Blog findByID(int id);

    void saveStatus(int id, String update, int status);

    void saveBlogChanges(Blog blog);

    Page<Blog> filterCategory(int id, int pageNo, int i);

    Page<Blog> filterStatus(int id, int pageNo, int i);
}
