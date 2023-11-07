package com.example.project.entity;

import java.util.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class patient {
    @Id
    private int patient_id;
    private int gender;
    private String image;
    //healthy
    //unhealthy
    private int status;
    
    private Date Dob;
    private String patient_name;
    private String description;
    private Date create_at;
    private String create_by;
    //session.name
    private Integer user_id;
    private String patient_email;
    private String patient_phone;
    private String patient_address;
    private LocalDate modify_at;

    public patient() {
    }

    public patient(int patient_id, int gender, String image, int status, Date dob, String patient_name,
            String description, Date create_at, String create_by, int user_id) {
        this.patient_id = patient_id;
        this.gender = gender;
        this.image = image;
        this.status = status;
        Dob = dob;
        this.patient_name = patient_name;
        this.description = description;
        this.create_at = create_at;
        this.create_by = create_by;
        this.user_id = user_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDescriptiob() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }

    public LocalDate getModify_at() {
        return modify_at;
    }

    public void setModify_at(LocalDate modify_at) {
        this.modify_at = modify_at;
    }

}
