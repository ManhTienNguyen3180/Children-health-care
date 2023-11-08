package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class feedbackreservation {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//     reservation_id int 
// rating int 
// comment text 
// FeedbackDate date
    private int reservation_id;
    private int rating;
    private String comment;
    @Column(name = "feedback_date")
    private Date date;
    private String Doctor_Knowledge;
    private String Nurse_Patience;
    private String Nurse_Knowledge;
    private String Waiting_Time;
    private String Hygiene;
    private String schedule_create;
    public feedbackreservation() {
    }
    public feedbackreservation(int id, int reservation_id, int rating, String comment, Date date) {
        this.id = id;
        this.reservation_id = reservation_id;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }
    
    
   
    public String getDoctor_Knowledge() {
        return Doctor_Knowledge;
    }
    public void setDoctor_Knowledge(String doctor_Knowledge) {
        Doctor_Knowledge = doctor_Knowledge;
    }
    public String getNurse_Patience() {
        return Nurse_Patience;
    }
    public void setNurse_Patience(String nurse_Patience) {
        Nurse_Patience = nurse_Patience;
    }
    public String getNurse_Knowledge() {
        return Nurse_Knowledge;
    }
    public void setNurse_Knowledge(String nurse_Knowledge) {
        Nurse_Knowledge = nurse_Knowledge;
    }
    public String getWaiting_Time() {
        return Waiting_Time;
    }
    public void setWaiting_Time(String waiting_Time) {
        Waiting_Time = waiting_Time;
    }
    public String getHygiene() {
        return Hygiene;
    }
    public void setHygiene(String hygiene) {
        Hygiene = hygiene;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getReservation_id() {
        return reservation_id;
    }
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getSchedule_create() {
        return schedule_create;
    }
    public void setSchedule_create(String schedule_create) {
        this.schedule_create = schedule_create;
    }

    
}
