package com.example.project.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class reservation {
    @Id
    private int reservation_id;
    private int patient_id;
    private int patient_name;
    @JoinColumn(name = "service_id")
    @Column(name = "service_id")
    private int service_id;
    private String service_name;
    private String doctor_name;
    private String email;
    private String phone;
    private Date date;
    private Time time;
    private String description;
    private int price;
    private int num_person;
    private int total_cost;
    private Date actual_date;
    private int status;
    private Date create_at;
    private String create_by;
    
    @ManyToOne    
    private service Service;

    public reservation() {
    }

    public reservation(int reservation_id, int patient_id, int patient_name, int service_id, String service_name,
            String doctor_name, String email, String phone, Date date, Time time, String description, int price,
            int num_person, int total_cost, Date actual_date, int status, Date create_at, String create_by) {
        this.reservation_id = reservation_id;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.service_id = service_id;
        this.service_name = service_name;
        this.doctor_name = doctor_name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.description = description;
        this.price = price;
        this.num_person = num_person;
        this.total_cost = total_cost;
        this.actual_date = actual_date;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
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

    public int getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(int patient_name) {
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

    public int getNum_person() {
        return num_person;
    }

    public void setNum_person(int num_person) {
        this.num_person = num_person;
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



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhone() {
        return phone;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    
    
}
