package com.example.project.Admin.BlogController.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Admin.BlogController.Model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

        // Insert Blog to database
        @Modifying
        @Transactional // If you need to delete, create or update anything in DB
        @Query(value = "INSERT INTO `childrencare_system`.`blog` (`category_blog_id`,`title`,`date`,`description`,`status`,`author`,`image`,`content`,`update_date`) \n"
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

        // Get search list
        @Query(value = "SELECT * FROM blog  WHERE (title LIKE %?1%"
                        + " OR description LIKE %?1%"
                        + " OR author LIKE %?1%) and status=1", nativeQuery = true)
        Page<Blog> search(@Param("searchText") String keyword, PageRequest pageable);

        @Modifying
        @Transactional
        @Query(value = "update blog set `update_date`= :update, `status`= :status where `blog_id`= :id", nativeQuery = true)
        void saveStatus(@Param("id") Integer id, @Param("update") String update, @Param("status") Integer status);

        @Modifying
        @Transactional
        @Query(value = "UPDATE blog SET `update_date`= :update, `category_blog_id` = :category, `title` = :title, `date` = :date, `description` = :description, `status` = :status, `author` = :author, `image` = :image,  `content` = :content WHERE `blog_id` = :id", nativeQuery = true)
        void saveBlogChanges(@Param("id") Integer id,
                        @Param("category") Integer category,
                        @Param("title") String title,
                        @Param("date") String date,
                        @Param("description") String description,
                        @Param("status") Integer status,
                        @Param("author") String author,
                        @Param("image") String image,
                        @Param("content") String content,
                        @Param("update") String update);

        @Query(value = "SELECT * FROM blog  WHERE `category_blog_id`= ?1", nativeQuery = true)
        Page<Blog> filterCategory(int id, PageRequest pageable);

        @Query(value = "SELECT * FROM blog  WHERE `status`= ?1", nativeQuery = true)
        Page<Blog> filterStatus(int id, PageRequest pageable);
}
