package com.example.project.Admin.TagController.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tag {
    @Id
    private int id;
    private String name;
    @Column(name = "blog_id")
    private int blogId;

    public Tag() {
    }

    public Tag(int id, String name, int blogId) {
        this.id = id;
        this.name = name;
        this.blogId = blogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

}
