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
public class patient {
    @Id
    private int patient_id;
    private int gender;
    private String image;
    private int status;
    private Date Dob;
    private String patient_name;
    private String descriptiob;
    private Date create_at;
    private String create_by;
    private int user_id;
    private String patient_email;
    private String patient_phone;
    private String patient_address;
    private String patient_note;

    

    
}
