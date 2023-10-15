package com.example.project.Admin.BlogController.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Admin.BlogController.Model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
        
        //Insert Blog to database
        @Modifying
        @Transactional // If you need to delete, create or update anything in DB
        @Query(value = "INSERT INTO `childrencare_system`.`blog` (`category_blog_id`,`title`,`date`,`description`,`status`,`author`,`image`,`content`,`update`) \n"
                        + //
                        "VALUES (:category,:title,:date,:description,:status,:author,:image,:content,:update);", nativeQuery = true)
        void insertBlog(@Param("category") Integer category,
                        @Param("title") String title,
                        @Param("date") String date,
                        @Param("description") String description,
                        @Param("status") Integer status,
                        @Param("author") String author,
                        @Param("image") String image,
                        @Param("content") String content,
                        @Param("update") String update);

}
