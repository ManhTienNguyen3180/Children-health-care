package com.example.project.Repository;

import java.sql.Date;
import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.reservation;

@Repository
public interface ReservationRepo extends JpaRepository<reservation, Integer> {
    @Query(value = "SELECT * FROM doctor d WHERE NOT EXISTS (SELECT s.service_id FROM service s WHERE s.service_id IN ?1 AND s.service_id NOT IN (SELECT dc.serviceID FROM doctorservice dc WHERE dc.doctorID = d.doctor_id))", nativeQuery = true)
    List<Object[]> findDoctorService(List<Integer> serviceIds);

    @Query(value = "select distinct s.dayof_week,s.doctor_id from slot s join doctor d on s.doctor_id = d.doctor_id where d.doctor_id = ?1", nativeQuery = true)
    List<Object[]> findDoctorSlot(int doctorid);

    @Query(value = "SELECT MAX(reservation_id) FROM reservation", nativeQuery = true)
    int getLastReservationId();

    @Query(value = "select count(reservation_id) from reservation r where r.doctor_id = ?1 and r.date = ?2", nativeQuery = true)
    int countByReservationId(int doctorid, Date date);

    @Query("SELECT u FROM reservation u where patient_id=?1")
    List<reservation> findByPatient_id(int patient_id);

    @Query("SELECT u FROM reservation u WHERE patient_id = ?1 AND date = ?2 ORDER BY date ASC")
    List<reservation> findByPatientDate(int patient_id, String date);

    @Query("select r from reservation r where r.doctor_id = ?1 and r.date = ?2 and r.time = ?3")
    reservation findByDoctor_idAndDateAndTime(int doctor_id, Date date, String time);

    @Query(nativeQuery = true, value = "WITH all_months AS (" +
            "SELECT 1 AS month " +
            "UNION ALL SELECT 2 " +
            "UNION ALL SELECT 3 " +
            "UNION ALL SELECT 4 " +
            "UNION ALL SELECT 5 " +
            "UNION ALL SELECT 6 " +
            "UNION ALL SELECT 7 " +
            "UNION ALL SELECT 8 " +
            "UNION ALL SELECT 9 " +
            "UNION ALL SELECT 10 " +
            "UNION ALL SELECT 11 " +
            "UNION ALL SELECT 12) " +
            "SELECT " +
            "    all_months.month AS month, " +
            "    COALESCE(COUNT(reservation.reservation_id), 0) AS number_of_reservations " +
            "FROM " +
            "    all_months " +
            "LEFT JOIN " +
            "   reservation AS reservation " +
            "    ON all_months.month = EXTRACT(MONTH FROM reservation.date) AND EXTRACT(YEAR FROM reservation.date) = ?1 "
            +
            "GROUP BY " +
            "    all_months.month " +
            "ORDER BY " +
            "    all_months.month;")
    List<Object[]> countReservationsByMonth(int year);

}
