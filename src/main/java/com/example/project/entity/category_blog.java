package com.example.project.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class category_blog {
    @Id
    private int category_blog_id;
    private String category_blog_name;
    private String description;
    private String status;
    private String create_at;
    private String create_by;


    public category_blog() {
    }
    public category_blog(int category_blog_id, String category_blog_name, String description, String status,
            String create_at, String create_by) {
        this.category_blog_id = category_blog_id;
        this.category_blog_name = category_blog_name;
        this.description = description;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
    }
    public int getCategory_blog_id() {
        return category_blog_id;
    }
    public void setCategory_blog_id(int category_blog_id) {
        this.category_blog_id = category_blog_id;
    }
    public String getCategory_blog_name() {
        return category_blog_name;
    }
    public void setCategory_blog_name(String category_blog_name) {
        this.category_blog_name = category_blog_name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreate_at() {
        return create_at;
    }
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
    public String getCreate_by() {
        return create_by;
    }
    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }
    @Override
    public String toString() {
        return "blog_category [category_blog_id=" + category_blog_id + ", category_blog_name=" + category_blog_name
                + ", description=" + description + ", status=" + status + ", create_at=" + create_at + ", create_by="
                + create_by + "]";
    }

    
}
