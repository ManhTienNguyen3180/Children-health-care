package com.example.project.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class slot {
    @Id
    private int id;
    private int doctor_id;
    private Date date;
    private Time start_time;
    private Time end_time;
    private int max_appointments_per_slot;
    private int dayof_week;
    public slot() {
    }
    
    public slot(int doctor_id, Time start_time, Time end_time, int dayof_week) {
        this.doctor_id = doctor_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.dayof_week = dayof_week;
    }

    public slot(int id, int doctor_id, Date date, Time start_time, Time end_time, int max_appointments_per_slot) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.max_appointments_per_slot = max_appointments_per_slot;
    }
    
    
    
    public slot(int id, int doctor_id, Date date, Time start_time, Time end_time, int max_appointments_per_slot,
            int dayof_week) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.max_appointments_per_slot = max_appointments_per_slot;
        this.dayof_week = dayof_week;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public int getMax_appointments_per_slot() {
        return max_appointments_per_slot;
    }

    public void setMax_appointments_per_slot(int max_appointments_per_slot) {
        this.max_appointments_per_slot = max_appointments_per_slot;
    }

    public int getDayof_week() {
        return dayof_week;
    }

    public void setDayof_week(int dayof_week) {
        this.dayof_week = dayof_week;
    }

    


}
