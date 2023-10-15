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
public class reservationdetail {
    @Id
    private int detailID;
    private int reservation_id;
    private int service_id;
    private String service_name;
    private int price;
    private Date create_at;
    private String create_by;
    private int doctor_id;
    private String doctor_name;

}
