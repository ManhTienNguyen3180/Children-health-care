package com.example.project.Admin.BlogController.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Page<Blog> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.repo.findAll(pageable);
    }

    @Override
    public Page<Blog> searchbyTitleAuthorordescription(String key, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo-1,pageSize);
        return this.repo.search(key, pageable);
        
     }

    @Override
    public Blog findByID(int id) {
        return repo.findById(id).get();
    }

    @Override
    public void saveStatus(int id, String udpdate, int status) {
        repo.saveStatus(id, udpdate, status);
    }

    @Override
    public void saveBlogChanges(Blog blog) {
        repo.saveBlogChanges(blog.getBlogId(), blog.getCategoryBlogId(), blog.getTitle(), blog.getDate().toString(), blog.getDescription(), blog.getStatus(), blog.getAuthor(), blog.getImage(), blog.getContent(), blog.getUpdateDate().toString());
    }

    @Override
    public Page<Blog> filterCategory(int id, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return repo.filterCategory(id, pageable);
    }

    @Override
    public Page<Blog> filterStatus(int id, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return repo.filterStatus(id, pageable);
    }

   

}
