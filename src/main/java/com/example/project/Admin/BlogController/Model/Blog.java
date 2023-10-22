package com.example.project.Admin.BlogController.Model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @Column(name = "blog_id")
    private int blogId;
    @Column(name = "category_blog_id")
    private int categoryBlogId;
    private String title;
    private Date date;
    private String description;
    private int status;
    private String author;
    private String image;
    private String content;
    @Column(name="updateDate")
    private Date updateDate;

    public Blog() {
    }



    public Blog(int blogId, int categoryBlogId, String title, Date date, String description, int status, String author,
            String image, String content, Date updatedate) {
        this.blogId = blogId;
        this.categoryBlogId = categoryBlogId;
        this.title = title;
        this.date = date;
        this.description = description;
        this.status = status;
        this.author = author;
        this.image = image;
        this.content = content;
        this.updateDate = updatedate;
    }



    public int getBlogId() {
        return blogId;
    }



    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }



    public int getCategoryBlogId() {
        return categoryBlogId;
    }



    public void setCategoryBlogId(int categoryBlogId) {
        this.categoryBlogId = categoryBlogId;
    }



    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public Date getDate() {
        return date;
    }



    public void setDate(Date date) {
        this.date = date;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public int getStatus() {
        return status;
    }



    public void setStatus(int status) {
        this.status = status;
    }



    public String getAuthor() {
        return author;
    }



    public void setAuthor(String author) {
        this.author = author;
    }



    public String getImage() {
        return image;
    }



    public void setImage(String image) {
        this.image = image;
    }



    public String getContent() {
        return content;
    }



    public void setContent(String content) {
        this.content = content;
    }



    public Date getUpdateDate() {
        return updateDate;
    }



    public void setUpdateDate(Date update) {
        this.updateDate = update;
    }

    
    
    
}

