  package com.example.project.entity;

  import java.time.LocalDate;

  import jakarta.persistence.*;

  @Entity
  
  @Table(name = "role")
  public class role {
    @Id
    
    private int role_id;
    private String role_name;
    private int status;
    private LocalDate date;
    private LocalDate create_at;
    private String create_by;

    public role() {

    }

    public role(String role_name, int role_status, LocalDate date, LocalDate create_at, String create_by) {
      this.role_name = role_name;
      this.status = role_status;
      this.date = date;
      this.create_at = create_at;
      this.create_by = create_by;
    }

    public int getRole_id() {
      return role_id;
    }

    public void setRole_id(int role_id) {
      this.role_id = role_id;
    }

    public String getRole_name() {
      return role_name;
    }

    public void setRole_name(String role_name) {
      this.role_name = role_name;
    }

    public int getRole_status() {
      return status;
    }

    public void setRole_status(int role_status) {
      this.status = role_status;
    }

    public LocalDate getDate() {
      return date;
    }

    public void setDate(LocalDate date) {
      this.date = date;
    }

    public LocalDate getCreate_at() {
      return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
      this.create_at = create_at;
    }

    public String getCreate_by() {
      return create_by;
    }

    public void setCreate_by(String create_by) {
      this.create_by = create_by;
    }

  }
