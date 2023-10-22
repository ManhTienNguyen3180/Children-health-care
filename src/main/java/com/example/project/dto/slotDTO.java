package com.example.project.dto;

public class slotDTO {
    private int id;
    private int doctor_id;
    private String date;
    private String start_time;
    private String end_time;
    private int MaxAppointmentsPerSlot;
    private int dayof_week;
    public slotDTO() {
    }
    public slotDTO(int id, int doctor_id, String date, int maxAppointmentsPerSlot) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.date = date;
        MaxAppointmentsPerSlot = maxAppointmentsPerSlot;
    }
    
    public slotDTO(int dayof_week) {
        this.dayof_week = dayof_week;
    }
    public slotDTO(String start_time, String end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }
    public slotDTO(int doctor_id, int dayof_week) {
        this.doctor_id = doctor_id;
        this.dayof_week = dayof_week;
    }
    public slotDTO(int id, int doctor_id, String date, String start_time, String end_time, int maxAppointmentsPerSlot,
            int dayof_week) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        MaxAppointmentsPerSlot = maxAppointmentsPerSlot;
        this.dayof_week = dayof_week;
    }
    public String getStart_time() {
        return start_time;
    }
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    public String getEnd_time() {
        return end_time;
    }
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getMaxAppointmentsPerSlot() {
        return MaxAppointmentsPerSlot;
    }
    public void setMaxAppointmentsPerSlot(int maxAppointmentsPerSlot) {
        MaxAppointmentsPerSlot = maxAppointmentsPerSlot;
    }
    public int getDayof_week() {
        return dayof_week;
    }
    public void setDayof_week(int dayof_week) {
        this.dayof_week = dayof_week;
    }
    
    
}
