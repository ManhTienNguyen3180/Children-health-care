package com.example.project.Repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.slot;

@Repository
public interface SlotRepo extends JpaRepository<slot, Integer>{

    @Query("SELECT s FROM slot s WHERE doctor_id=?1 ")
    Optional<slot> findSlotByDoctorId(int doctor_id);

    @Query("SELECT s FROM slot s WHERE doctor_id=?1 ")
      List<slot> findByDoctorId(int doctorId);
}
