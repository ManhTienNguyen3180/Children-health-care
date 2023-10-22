package com.example.project.dto;

public class doctorserviceDTO {
    private int doctor_id;
    private String doctor_name;
    private String position;
    private int gender;
    private int phone;
    private String image;
    private String description;
    private String dob;
    private int status;
    private String create_at;
    private String create_by;
    public doctorserviceDTO() {
    }
    public doctorserviceDTO(int doctor_id, String doctor_name, String position, int gender, int phone, String image,
            String description, String dob, int status, String create_at, String create_by) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.position = position;
        this.gender = gender;
        this.phone = phone;
        this.image = image;
        this.description = description;
        this.dob = dob;
        this.status = status;
        this.create_at = create_at;
        this.create_by = create_by;
    }
    public int getDoctor_id() {
        return doctor_id;
    }
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    public String getDoctor_name() {
        return doctor_name;
    }
    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCreate_at() {
        return create_at;
    }
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
    public String getCreate_by() {
        return create_by;
    }
    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }
    
}
