package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class service {

    @Id
    private int service_id;
    private String service_name;
    private int price;
    private String description; 
    private String img;
    private int category_id;
    private int status;
    private Date create_at;
    private String create_by;

    
    // @OneToMany(mappedBy = "service")
    // private List<reservation> reservation;


    public service() {
    }


    public service(int service_id, String service_name, int price, String description, String img, int category_id,
            int status, Date create_at, String create_by, List<com.example.project.entity.reservation> reservation) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.category_id = category_id;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
        
    }

    public int getService_id() {
        return service_id;
    }


    public void setService_id(int service_id) {
        this.service_id = service_id;
    }


    public String getService_name() {
        return service_name;
    }


    public void setService_name(String service_name) {
        this.service_name = service_name;
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


    public String getImg() {
        return img;
    }


    public void setImg(String img) {
        this.img = img;
    }


    public int getCategory_id() {
        return category_id;
    }


    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }


    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
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


    
    
    

    
}
