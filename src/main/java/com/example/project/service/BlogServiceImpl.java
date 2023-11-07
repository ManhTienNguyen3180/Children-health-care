package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.Admin.BlogController.Model.Blog;
import com.example.project.Admin.BlogController.Service.BlogsServiceImpl;
import com.example.project.Repository.BlogRepo;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo repo;

    @Override
    public List<Blog> fetchBLogList() {
        return (List<Blog>) repo.findAll();
    }

    @Override
    public Optional<Blog> findBlogById(int id) {
        return (Optional<Blog>) repo.findById(id);

    }

    // Save Blog
    @Override
    public void save(Blog blog) {
        repo.save(blog);
    }

    // Delete blog
    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Blog> getBlogByCategoryId(int category_id) {
        return repo.findByCategory_blog_id(category_id);
    }

    @Override
    public List<Blog> getBlogsNew() {
        return repo.getBlogNew();
    }

    @Override
    public Page<Blog> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(pageNo-1,pageSize,sort);
        
        return this.repo.findAll(pageable);
    }

    //search
    @Override
    public List<Blog> findBlogByTitleOrTags(String key) {
        
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
    public Page<Blog> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo-1,pageSize);
        return this.repo.findAllPagi(pageable);
    }

    @Override
    public List<Blog> getBlogByTags(String name) {
        return repo.getBlogByTags(name);
    }

    //page phan trang
    @Override
    public Page<Blog> findBlogByTitleOrTagsPaged(String key, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.findByTitleOrTags(key,pageable);
    }

    @Override
    public Page<Blog> getBlogByCategoryIdPaged(int category_blog_id, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.findByCategory_blog_id(category_blog_id,pageable);
    }

    @Override
    public Page<Blog> getBlogByTagsPaged(String name, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.getBlogByTags(name,pageable);
    }

    @Override
    public Page<Object> getComment(int blogId, int page, int size) {
        PageRequest pageable = PageRequest.of(page-1,size);
        return this.repo.getComment(blogId,pageable);
    }

    

    

    @Override
    public List<Object[]> GetBlogAndCategory() {
        return repo.findBlogAndCategory();
    }


}
