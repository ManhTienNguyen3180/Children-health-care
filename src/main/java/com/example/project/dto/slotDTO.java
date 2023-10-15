package com.example.project.dto;

public class slotDTO {
    private int id;
    private int doctor_id;
    private String date;
    private int MaxAppointmentsPerSlot;
    public slotDTO() {
    }
    public slotDTO(int id, int doctor_id, String date, int maxAppointmentsPerSlot) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.date = date;
        MaxAppointmentsPerSlot = maxAppointmentsPerSlot;
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
    
}
