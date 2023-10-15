package com.example.project.Admin.SliderController.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "slider")
public class Slider {
    @Id
    private int id;
    private String title;
    private String image;
    private String backlink;
    private int status;
    @Column(name = "createat")
    private Date createDate;
    @Column(name = "slider_id")
    private int position;

    public Slider() {
    }

    public Slider(String title, String image, String backlink, int status, Date createDate, int position) {
        this.title = title;
        this.image = image;
        this.backlink = backlink;
        this.status = status;
        this.createDate = createDate;
        this.position = position;
    }

    public Slider(int id, String title, String image, String backlink, int status, Date createDate, int position) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.backlink = backlink;
        this.status = status;
        this.createDate = createDate;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
