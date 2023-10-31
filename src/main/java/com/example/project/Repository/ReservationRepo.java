package com.example.project.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Query("select r from reservation r  join slot s on r.doctor_id = s.doctor_id where r.doctor_id = ?1 and r.date = ?2 and r.time = ?3 and s.dayof_week = ?4 and r.status not in (2,4)")
    reservation findByDoctor_idAndDateAndTimeAndDay(int doctor_id, Date date, String time,int dayof_week);

    @Query(value = "select r.reservation_id,r.patient_name,p.patient_email,p.dob,p.gender,r.date,r.time,r.doctor_name,r.status from reservation r left join patient p on r.patient_id = p.patient_id", nativeQuery = true)
    Page<Object[]> getListReservation(PageRequest pageable);

    
}
