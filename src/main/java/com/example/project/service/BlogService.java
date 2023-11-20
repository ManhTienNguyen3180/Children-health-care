package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.project.Admin.BlogController.Model.Blog;


public interface BlogService {

    List<Blog> fetchBLogList();

    List<Object[]> GetBlogAndCategory();
    //Get Blog by ID
    Optional<Blog> findBlogById(int id);

    //Save blog
    void save(Blog blog);

    //Detele blog
    void delete(int id);

    //get list blog by cate_id
    List<Blog> getBlogByCategoryId(int blog_id,int category_id);

    //get list blog by status
    List<Blog> getBlogsNew();

    //implement paginate
    Page<Blog> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    
    //search theo title va tags
    List<Blog> findBlogByTitleOrTags(String key);

    //get BLog by Tags
    List<Blog> getBlogByTags(String name);

    //get cmt
    List<Object> getComment(int blogId);
    List<Object> getTags(int blogId);


    //phan trang blog list(public)
    Page<Blog> findPaginated(int pageNo, int pageSize);

    Page<Blog> findBlogByTitleOrTagsPaged(String key, int page, int size);

    Page<Blog> getBlogByCategoryIdPaged(int category_blog_id, int page, int size);

    Page<Blog> getBlogByTagsPaged(String key, int page, int size);

    Page<Object> getComment(int blogId,int page, int size);
}
