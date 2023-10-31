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
    void mergeReservationDetail(int reservation_id, int service_id, String service_name, int price, Date create_at, String create_by, int doctor_id, String doctor_name);

    @Query("select rd from reservationdetail rd where rd.reservation_id = ?1")
    List<reservationdetail> findByReservation_id(int reservation_id);
}
