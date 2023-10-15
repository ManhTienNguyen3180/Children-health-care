package com.example.project.Admin.ServiceController.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @Column(name = "service_id")
    private int Id;
    @Column(name = "service_name")
    private String name;
    private int price;
    private String description;
    @Column(name = "img")
    private String image;
    @Column(name = "category_id")
    private int categoryId;
    private int status;
    @Column(name = "create_at")
    private Date createDate;
    @Column(name = "create_by") 
    private String createBy;

    public Service() {
    }
 
    public Service(String name, int price, String description, String image, int categoryId, int status,
            Date createDate, String createBy) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
        this.status = status;
        this.createDate = createDate;
        this.createBy = createBy;
    }

    public Service(int id, String name, int price, String description, String image, int categoryId, int status,
            Date createDate, String createBy) {
        Id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
        this.status = status;
        this.createDate = createDate;
        this.createBy = createBy;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

}
