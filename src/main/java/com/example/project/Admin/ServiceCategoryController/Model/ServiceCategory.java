package com.example.project.Admin.ServiceCategoryController.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category_service")
public class ServiceCategory {
    @Id
    private int id;
    private String name;
    private String note;
    private int status;
    @Column(name = "create_at")
    private String createAt;
    @Column(name = "create_by")
    private String createBy;
    public ServiceCategory() {
    }
    public ServiceCategory(String name, String note, int status, String createAt, String createBy) {
        this.name = name;
        this.note = note;
        this.status = status;
        this.createAt = createAt;
        this.createBy = createBy;
    }
    public ServiceCategory(int id, String name, String note, int status, String createAt, String createBy) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.status = status;
        this.createAt = createAt;
        this.createBy = createBy;
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
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCreateAt() {
        return createAt;
    }
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    public String getCreateBy() {
        return createBy;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
}