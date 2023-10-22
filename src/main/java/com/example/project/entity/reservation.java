package com.example.project.entity;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class reservation {
    @Id
    private int reservation_id;
    private int patient_id;
    private String patient_name;
    private int service_id;
    private String service_name;
    private String doctor_name;
    private Date date;
    private String description;
    private int price;
    private int total_cost;
    private Date actual_date;
    private int status;
    private Date create_at;
    private String create_by;
    private int doctor_id;
    private String time;
    public reservation() {
    }
    public reservation(int reservation_id, int patient_id, String patient_name, int service_id, String service_name,
            String doctor_name, Date date, String description, int price, int total_cost, Date actual_date, int status,
            Date create_at, String create_by, int doctor_id) {
        this.reservation_id = reservation_id;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.service_id = service_id;
        this.service_name = service_name;
        this.doctor_name = doctor_name;
        this.date = date;
        this.description = description;
        this.price = price;
        this.total_cost = total_cost;
        this.actual_date = actual_date;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
        this.doctor_id = doctor_id;
    }
    
    public reservation(int reservation_id, int patient_id, String patient_name, int service_id, String service_name,
            String doctor_name, Date date, String description, int price, int total_cost, Date actual_date, int status,
            Date create_at, String create_by, int doctor_id, String time) {
        this.reservation_id = reservation_id;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.service_id = service_id;
        this.service_name = service_name;
        this.doctor_name = doctor_name;
        this.date = date;
        this.description = description;
        this.price = price;
        this.total_cost = total_cost;
        this.actual_date = actual_date;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
        this.doctor_id = doctor_id;
        this.time = time;
    }
    
    public int getReservation_id() {
        return reservation_id;
    }
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }
    public int getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    public String getPatient_name() {
        return patient_name;
    }
    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
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
    public String getDoctor_name() {
        return doctor_name;
    }
    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
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
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getTotal_cost() {
        return total_cost;
    }
    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }
    public Date getActual_date() {
        return actual_date;
    }
    public void setActual_date(Date actual_date) {
        this.actual_date = actual_date;
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
    public int getDoctor_id() {
        return doctor_id;
    }
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    
    
    
    
    
    
    
    

    
    

    

    

    
    
}
