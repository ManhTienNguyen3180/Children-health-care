package com.example.project.Repository;

import com.example.project.entity.reservationdetail;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReservationDetailRepo extends JpaRepository<reservationdetail, Integer>{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO reservationdetail (reservation_id, service_id, service_name, price,create_at,create_by,doctor_id,doctor_name) VALUES (?1, ?2, ?3, ?4, ?5, ?6,?7,?8)", nativeQuery = true)
    void mergeReservationDetail(int reservation_id, int service_id, String service_name, int price, Date create_at,
            String create_by, int doctor_id, String doctor_name);

    @Query(value = "select r.reservation_id,r.patient_name,p.patient_email,p.dob,p.gender,r.date,r.time,r.doctor_name,r.status,p.patient_phone,p.patient_address,r.description,r.patient_id from reservation r right join patient p on r.patient_id = p.patient_id where r.reservation_id = ?1", nativeQuery = true)
    List<Object[]> getReservationDetail(int reservation_id);

    @Query("select rd from reservationdetail rd where rd.reservation_id = ?1")
    List<reservationdetail> getlistDetail(int reservation_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE reservation SET status = ?2 WHERE reservation_id = ?1", nativeQuery = true)
    void editReservation(int id, int statusedit);

    @Transactional
    @Modifying
    @Query(value = "UPDATE reservation SET doctor_name = ?2,doctor_id=?1 WHERE reservation_id = ?3", nativeQuery = true)
    void editReservationDoc(int docid, String docname,int id);

    @Transactional
    @Modifying
    @Query(value = "delete from reservationdetail r where r.reservation_id = ?1 and r.service_id = ?2", nativeQuery = true)
    void DeleteService(int reid,int serviceid);


    @Query("select rd from reservationdetail rd where rd.reservation_id = ?1")
    List<reservationdetail> findByReservation_id(int reservation_id);
}
