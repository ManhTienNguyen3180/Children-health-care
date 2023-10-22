package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class category_service {
    @Id
    private int id;
    private String name;
    private String note;
    private int status;
    private Date create_at;
    private String create_by;
    public category_service() {
    }
    public category_service(int id, String name, String note, int status, Date create_at, String create_by) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
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
