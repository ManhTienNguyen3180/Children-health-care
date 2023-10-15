package com.example.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.reservation;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepo extends JpaRepository<reservation, Integer> {
      @Query("SELECT u FROM reservation u where patient_id=?1")
      List<reservation> findByPatient_id(int patient_id);

}
