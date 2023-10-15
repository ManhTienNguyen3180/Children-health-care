package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.project.entity.blog;
import com.example.project.entity.review_blog;
import com.example.project.entity.tags;


public interface BlogService {

    List<blog> fetchBLogList();

    //Get Blog by ID
    Optional<blog> findBlogById(int id);

    //Save blog
    void save(blog blog);

    //Detele blog
    void delete(int id);

    //get list blog by cate_id
    List<blog> getBlogByCategoryId(int category_id);

    //get list blog by status
    List<blog> getBlogsNew();

    //implement paginate
    Page<blog> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    
    //search theo title va tags
    List<blog> findBlogByTitleOrTags(String key);

    //get BLog by Tags
    List<blog> getBlogByTags(String name);

    //get cmt
    List<Object> getComment(int blogId);
    List<Object> getTags(int blogId);


    //phan trang blog list(public)
    Page<blog> findPaginated(int pageNo, int pageSize);

    Page<blog> findBlogByTitleOrTagsPaged(String key, int page, int size);

    Page<blog> getBlogByCategoryIdPaged(int category_blog_id, int page, int size);

    Page<blog> getBlogByTagsPaged(String key, int page, int size);

    Page<Object> getComment(int blogId,int page, int size);
}
