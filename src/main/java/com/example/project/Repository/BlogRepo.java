package com.example.project.Repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.blog;


@Repository
public interface BlogRepo extends JpaRepository<blog, Integer>{
    
    @Query(value = "SELECT * FROM blog b WHERE b.category_blog_id = ?1",nativeQuery = true)
    List<blog> findByCategory_blog_id(int category_blog_id);
    
    @Query( value ="select * from blog b  order by b.date desc limit 3",nativeQuery = true)
    List<blog> getBlogNew();
    
}
