package com.example.project.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.slot;

import jakarta.transaction.Transactional;

@Repository
public interface SlotRepo extends JpaRepository<slot, Integer>{

    @Query("SELECT s FROM slot s WHERE doctor_id=?1 ")
    Optional<slot> findSlotByDoctorId(int doctor_id);

    @Query("SELECT s FROM slot s WHERE doctor_id=?1 ")
    List<slot> findByDoctorId(int doctorId);


    @Query(value = "select s.start_time,s.end_time from slot s where s.doctor_id = ?1 and s.dayof_week = ?2")
    List<Object[]> findSlotByDoctorIdAndDayOfWeek(int doctorId, int dayOfWeek);

    @Query(value = "select s.dayof_week from slot s group by s.dayof_week order by s.dayof_week", nativeQuery = true)
    List<Object[]> findDayOfWeek();

    @Query(value = "select s.start_time,s.end_time from slot s where s.dayof_week = ?1", nativeQuery = true)
    List<Object[]> findTime(int dayOfWeek);

    @Query("select s from slot s where s.dayof_week = ?1 and s.doctor_id = ?2 and not (?3 <= start_time OR ?4 >= end_time)")
    List<slot> checkSlotByDoctorIdAndDayOfWeekAndTime(int dayOfWeek, int doctorId, Time endTime, Time startTime);
}
