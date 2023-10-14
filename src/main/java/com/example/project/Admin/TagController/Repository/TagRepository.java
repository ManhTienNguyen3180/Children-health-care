package com.example.project.Admin.TagController.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Admin.TagController.Model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
    
}
