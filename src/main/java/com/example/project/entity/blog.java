package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class blog {
    @Id
    private int blog_id;
    private int category_blog_id;
    private String title;
    private Date date;
    private String description;
    private int status;
    private String author;
    private String image;
    private String content;
    private Date updateDate;


    public blog() {
    }

    public blog(int blog_id, int category_blog_id, String title, Date date, String description, int status,
            String author, String image) {
        this.blog_id = blog_id;
        this.category_blog_id = category_blog_id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.status = status;
        this.author = author;
        this.image = image;
    }

    

    public blog(int blog_id, int category_blog_id, String title, Date date, String description, int status,
            String author, String image, String content, Date update) {
        this.blog_id = blog_id;
        this.category_blog_id = category_blog_id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.status = status;
        this.author = author;
        this.image = image;
        this.content = content;
        this.updateDate = update;
    }

    public int getBlog_id() {
        return blog_id;
    }


    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }



    public int getCategory_blog_id() {
        return category_blog_id;
    }



    public void setCategory_blog_id(int category_blog_id) {
        this.category_blog_id = category_blog_id;
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
