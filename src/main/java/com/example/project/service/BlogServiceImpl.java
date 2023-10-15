package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.Repository.BlogRepo;
import com.example.project.entity.blog;
import com.example.project.entity.review_blog;
import com.example.project.entity.tags;

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

    //search
    @Override
    public List<blog> findBlogByTitleOrTags(String key) {
        
        return repo.findByTitleOrTags(key);
    }

    @Override
    public List<Object> getComment(int blogId) {
        return repo.getComment(blogId);
    }

    @Override
    public List<Object> getTags(int blogId) {
        return repo.getTags(blogId);
    }

    @Override
    public Page<blog> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo-1,pageSize);
        return this.repo.findAll(pageable);
    }

    @Override
    public List<blog> getBlogByTags(String name) {
        return repo.getBlogByTags(name);
    }

    //page phan trang
    @Override
    public Page<blog> findBlogByTitleOrTagsPaged(String key, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.findByTitleOrTags(key,pageable);
    }

    @Override
    public Page<blog> getBlogByCategoryIdPaged(int category_blog_id, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.findByCategory_blog_id(category_blog_id,pageable);
    }

    @Override
    public Page<blog> getBlogByTagsPaged(String name, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.getBlogByTags(name,pageable);
    }

    @Override
    public Page<Object> getComment(int blogId, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.getComment(blogId,pageable);
    }

    

    

}
