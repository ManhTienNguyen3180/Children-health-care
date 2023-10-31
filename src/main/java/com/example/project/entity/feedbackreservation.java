package com.example.project.entity;

import java.sql.Date;

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
    private Date date;
    
    public feedbackreservation() {
    }
    public feedbackreservation(int id, int reservation_id, int rating, String comment, Date date) {
        this.id = id;
        this.reservation_id = reservation_id;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
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

    
}
