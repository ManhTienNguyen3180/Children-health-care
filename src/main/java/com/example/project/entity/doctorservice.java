package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class doctorservice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int doctorID;
    private int serviceID;
    private int status;
    
    public doctorservice() {
    }

    public doctorservice(int id, int doctorID, int serviceID, int status) {
        this.id = id;
        this.doctorID = doctorID;
        this.serviceID = serviceID;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
}
