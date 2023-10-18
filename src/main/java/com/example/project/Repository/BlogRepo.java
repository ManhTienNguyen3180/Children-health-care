package com.example.project.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.Admin.BlogController.Model.Blog;


@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer>{
    
    @Query(value = "SELECT * FROM blog b WHERE b.category_blog_id = ?1",nativeQuery = true)
    List<Blog> findByCategory_blog_id(int category_blog_id);
    
    @Query( value ="select * from blog b  order by b.date desc limit 3",nativeQuery = true)
    List<Blog> getBlogNew();

    @Query(value ="select * from blog b join category_blog c on b.category_blog_id = c.category_blog_id",nativeQuery = true)
    List<Object[]> findBlogAndCategory();

    
    //search
    @Query(value ="select b.blog_id,b.category_blog_id,b.title,b.date,\n" + //
            "b.description,b.status,b.author,b.image,b.content,b.update from blog b join tags t on b.blog_id=t.id\n" + //
            "and (b.title LIKE %:key% or t.name LIKE %:key%)",nativeQuery = true)
    List<Blog> findByTitleOrTags(@Param("key")String key);

    

    //comment
    @Query( value ="select * from review_blog r join user u on r.user_id=u.user_id where r.blog_id=?1 order by r.date desc",nativeQuery = true)
    List<Object> getComment(int blogId);

    @Query( value ="select * from tags where blog_id=?1",nativeQuery = true)
    List<Object> getTags(int blogId);
    
    
    @Query( value ="select b.blog_id,b.category_blog_id,b.title,b.date,\n" + //
            "b.description,b.status,b.author,b.image,b.content,b.update from blog b join tags t on b.blog_id=t.blog_id\n" + //
            "where t.name like %:name%",nativeQuery = true)
    List<Blog> getBlogByTags(@Param("name")String name);



    //phan trang blog list(public)
    @Query(value = "SELECT * FROM blog b WHERE b.category_blog_id = ?1",nativeQuery = true)
    Page<Blog> findByCategory_blog_id(int category_blog_id,PageRequest pageable);

    @Query( value ="select b.blog_id,b.category_blog_id,b.title,b.date,\n" + //
            "b.description,b.status,b.author,b.image,b.content,b.update_date from blog b join tags t on b.blog_id=t.blog_id\n" + //
            "where t.name like %:name%",nativeQuery = true)
    Page<Blog> getBlogByTags(@Param("name")String name,PageRequest pageable);

    @Query(value ="select b.blog_id,b.category_blog_id,b.title,b.date,\n" + //
            "b.description,b.status,b.author,b.image,b.content,b.update_date from blog b join tags t on b.blog_id=t.id\n" + //
            "and (b.title LIKE %:key% or t.name LIKE %:key%)",nativeQuery = true)
    Page<Blog> findByTitleOrTags(@Param("key")String key,PageRequest pageable);

    @Query( value ="select * from review_blog r join user u on r.user_id=u.user_id where r.blog_id=?1 order by r.date desc",nativeQuery = true)
    Page<Object> getComment(int blogId,PageRequest pageable);
}
