package com.example.project.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.patient;
import com.example.project.entity.reservation;

@Repository
public interface ReservationRepo extends JpaRepository<reservation, Integer> {
    @Query(value = "SELECT * FROM doctor d WHERE NOT EXISTS (SELECT s.service_id FROM service s WHERE s.service_id IN ?1 AND s.service_id NOT IN (SELECT dc.serviceID FROM doctorservice dc WHERE dc.doctorID = d.doctor_id))", nativeQuery = true)
    List<Object[]> findDoctorService(List<Integer> serviceIds);

    @Query(value = "select distinct s.dayof_week,s.doctor_id from slot s join doctor d on s.doctor_id = d.doctor_id where d.doctor_id = ?1", nativeQuery = true)
    List<Object[]> findDoctorSlot(int doctorid);

    @Query(value = "SELECT MAX(reservation_id) FROM reservation", nativeQuery = true)
    int getLastReservationId();

    @Query(value = "select count(r.reservation_id) from reservation r where r.doctor_id = ?1 and r.date = ?2 and r.time = ?3 and r.status not in (2,4)", nativeQuery = true)
    int countByReservationId(int doctor_id, Date date, String time);

    @Query("SELECT u FROM reservation u where patient_id=?1 ")
    Optional<reservation> findByPatient(int patient_id, int reservation_id);

    @Query("SELECT u FROM reservation u where patient_id=?1")
    List<reservation> findByPatient_id(int patient_id);

    @Query("SELECT u FROM reservation u where patient_id=?1")
    Optional<reservation> findReByPid(int patient_id);

    @Query("SELECT u FROM reservation u WHERE patient_id = ?1 AND date = ?2 ORDER BY date ASC")
    List<reservation> findByPatientDate(int patient_id, String date);

    @Query("select r from reservation r where r.doctor_id = ?1 and r.date = ?2 and r.time = ?3 and r.status not in (2,4)")
    reservation findByDoctor_idAndDateAndTime(int doctor_id, Date date, String time);

    @Query("select r from reservation r  join slot s on r.doctor_id = s.doctor_id where r.doctor_id = ?1 and r.date = ?2 and r.time = ?3 and s.dayof_week = ?4 and r.status not in (2,4)")
    reservation findByDoctor_idAndDateAndTimeAndDay(int doctor_id, Date date, String time, int dayof_week);

    @Query(value = "select r.reservation_id,r.patient_name,p.patient_email,p.dob,p.gender,r.date,r.time,r.doctor_name,r.status from reservation r left join patient p on r.patient_id = p.patient_id order by r.status", nativeQuery = true)
    Page<Object[]> getListReservation(PageRequest pageable);

    @Query(value = "select r.reservation_id,r.patient_name,p.patient_email,p.dob,p.gender,r.date,r.time,r.doctor_name,r.status from reservation r left join patient p on r.patient_id = p.patient_id where r.doctor_name = ?1 and r.status in (1,2,3,4) order by r.status", nativeQuery = true)
    List<Object[]> getListReservationByName(String name);

    @Query(value = "select r.reservation_id,r.patient_name,p.patient_email,p.dob,p.gender,r.date,r.time,r.doctor_name,r.status from reservation r left join patient p on r.patient_id = p.patient_id where r.status = ?1", nativeQuery = true)
    Page<Object[]> getListReservationByStatus(int status, PageRequest pageable);

    @Query("select r from reservation r join patient p on r.patient_id = p.patient_id where p.user_id = ?1 order by r.reservation_id desc")
    List<reservation> findReservationByUserId(int user_id);

    @Query("select r from reservation r join patient p on r.patient_id = p.patient_id where p.user_id = ?1")
    Page<reservation> findPageReservationByUserId(int user_id, PageRequest pageable);

    @Query(value = "SELECT COALESCE(COUNT(r.reservation_id), 0) AS number_of_reservations " +
            "FROM (SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 " +
            "UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 " +
            "UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 " +
            "UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12) AS months " +
            "LEFT JOIN childrencare_system.reservation AS r " +
            "ON months.month = EXTRACT(MONTH FROM r.date) AND EXTRACT(YEAR FROM r.date) = :year " +
            "GROUP BY months.month " +
            "ORDER BY months.month", nativeQuery = true)
    List<Integer> getMonthlyReservationCounts(@Param("year") int year);
}
