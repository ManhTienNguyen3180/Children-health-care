package com.example.project.Admin.CategoryController.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category_blog")
public class BlogCategory {
    @Id
    @Column(name = "category_blog_id")
    private int categoryBlogId;
    @Column(name = "category_blog_name")
    private String categoryBlogName;
    private String description;
    private String status;
    private String create_at;
    private String create_by;


    
    @Override
    public String toString() {
        return "BlogCategory [categoryBlogId=" + categoryBlogId + ", categoryBlogName=" + categoryBlogName
                + ", description=" + description + ", status=" + status + ", create_at=" + create_at + ", create_by="
                + create_by + "]";
    }

    public BlogCategory() {
    }

    public BlogCategory(int categoryBlogId, String categoryBlogName, String description, String status,
            String create_at, String create_by) {
        this.categoryBlogId = categoryBlogId;
        this.categoryBlogName = categoryBlogName;
        this.description = description;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
    }

    public int getCategoryBlogId() {
        return categoryBlogId;
    }

    public void setCategoryBlogId(int categoryBlogId) {
        this.categoryBlogId = categoryBlogId;
    }

    public String getCategoryBlogName() {
        return categoryBlogName;
    }

    public void setCategoryBlogName(String categoryBlogName) {
        this.categoryBlogName = categoryBlogName;
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
    

    
}