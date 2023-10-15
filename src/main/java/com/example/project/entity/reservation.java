package com.example.project.entity;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class reservation {
    @Id
    private int reservation_id;
    private int patient_id;
    private String patient_name;
    private int doctor_id;
    private String doctor_name;
    private Date date;
    private String description;
    private int price;
    private int total_cost;
    private Date actual_date;
    private int status;
    private Date create_at;
    private String create_by;
    
    
}
