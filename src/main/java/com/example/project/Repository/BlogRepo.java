package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.blog;


@Repository
public interface BlogRepo extends JpaRepository<blog, Integer>{

    
    
}
