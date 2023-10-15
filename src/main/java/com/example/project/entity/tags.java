package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class tags {
    @Id
    private int id;
    private String name;
    private int blog_id;
    
    public tags() {
    }
    public tags(int id, String name, int blog_id) {
        this.id = id;
        this.name = name;
        this.blog_id = blog_id;
    }
    public int getId() {
        return id;
    }
    public void setTags_id(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBlog_id() {
        return blog_id;
    }
    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    
}
