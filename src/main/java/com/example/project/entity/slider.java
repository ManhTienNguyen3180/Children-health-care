package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class slider {
    @Id
    private int slider_id;
    private String title;
    private String image;
    private String backlink;
    private int status;
    public slider() {
    }
    public slider(int slider_id, String title, String image, String backlink, int status) {
        this.slider_id = slider_id;
        this.title = title;
        this.image = image;
        this.backlink = backlink;
        this.status = status;
    }
    public int getSlider_id() {
        return slider_id;
    }
    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getBacklink() {
        return backlink;
    }
    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    
}
