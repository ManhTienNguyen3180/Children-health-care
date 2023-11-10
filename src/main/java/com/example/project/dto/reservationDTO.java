package com.example.project.dto;

import java.util.Date;

public class reservationDTO {
    private int reservation_id;
    private int patient_id;
    private String patient_name;
    private String patient_email;
    private int patient_phone;
    private String patient_address;
    private String note;
    private Date dob; 
    private int gender;
    private Date date;
    private String time;
    private String doctor_name;
    private int status;
    public reservationDTO() {
    }
    public reservationDTO(int reservation_id, String patient_name, String patient_email, Date dob, int gender,
            Date date, String time, String doctor_name, int status) {
        this.reservation_id = reservation_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.dob = dob;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.doctor_name = doctor_name;
        this.status = status;
    }
    
    public reservationDTO(int reservation_id, int patient_id, String patient_name, String patient_email,
            int patient_phone, String patient_address, String note, Date dob, int gender, Date date, String time,
            String doctor_name, int status) {
        this.reservation_id = reservation_id;
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_phone = patient_phone;
        this.patient_address = patient_address;
        this.note = note;
        this.dob = dob;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.doctor_name = doctor_name;
        this.status = status;
    }
    public reservationDTO(int reservation_id, String patient_name, String patient_email, int patient_phone, Date dob,
            int gender, Date date, String time, String doctor_name, int status) {
        this.reservation_id = reservation_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_phone = patient_phone;
        this.dob = dob;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.doctor_name = doctor_name;
        this.status = status;
    }
    
    public reservationDTO(int reservation_id, String patient_name, String patient_email, int patient_phone,
            String patient_address, Date dob, int gender, Date date, String time, String doctor_name, int status) {
        this.reservation_id = reservation_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_phone = patient_phone;
        this.patient_address = patient_address;
        this.dob = dob;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.doctor_name = doctor_name;
        this.status = status;
    }
    
    public reservationDTO(int reservation_id, String patient_name, String patient_email, int patient_phone,
            String patient_address, String note, Date dob, int gender, Date date, String time, String doctor_name,
            int status) {
        this.reservation_id = reservation_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_phone = patient_phone;
        this.patient_address = patient_address;
        this.note = note;
        this.dob = dob;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.doctor_name = doctor_name;
        this.status = status;
    }
    
    public reservationDTO(int reservation_id, String patient_name, String patient_email, int patient_phone,
            String patient_address, String note, int gender, Date date, String time, String doctor_name, int status) {
        this.reservation_id = reservation_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_phone = patient_phone;
        this.patient_address = patient_address;
        this.note = note;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.doctor_name = doctor_name;
        this.status = status;
    }
    public int getReservation_id() {
        return reservation_id;
    }
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }
    public String getPatient_name() {
        return patient_name;
    }
    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
    public String getPatient_email() {
        return patient_email;
    }
    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }
    public Date getDob() {
        return dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDoctor_name() {
        return doctor_name;
    }
    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getPatient_phone() {
        return patient_phone;
    }
    public void setPatient_phone(int patient_phone) {
        this.patient_phone = patient_phone;
    }
    public String getPatient_address() {
        return patient_address;
    }
    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    
}
