package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class review_blog {
    @Id
    private int review_id;
    private int blog_id;
    private Date date;
    private String text;
    private int user_id;
    private Date create_at;
    private String create_by;
    private int status;
    public review_blog() {
    }
    public review_blog(int review_id, int blog_id, Date date, String text, int user_id, Date create_at,
            String create_by, int status) {
        this.review_id = review_id;
        this.blog_id = blog_id;
        this.date = date;
        this.text = text;
        this.user_id = user_id;
        this.create_at = create_at;
        this.create_by = create_by;
        this.status = status;
    }
    public int getReview_id() {
        return review_id;
    }
    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }
    public int getBlog_id() {
        return blog_id;
    }
    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public Date getCreate_at() {
        return create_at;
    }
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
    public String getCreate_by() {
        return create_by;
    }
    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    
}
