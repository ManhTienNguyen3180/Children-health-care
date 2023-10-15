package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    // Save Blog
    @Override
    public void save(blog blog) {
        repo.save(blog);
    }

    // Delete blog
    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<blog> getBlogByCategoryId(int category_id) {
        return repo.findByCategory_blog_id(category_id);
    }

    @Override
    public List<blog> getBlogsNew() {
        return repo.getBlogNew();
    }

    @Override
    public Page<blog> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(pageNo-1,pageSize,sort);
        
        return this.repo.findAll(pageable);
    }

}
