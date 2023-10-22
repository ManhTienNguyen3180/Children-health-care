package com.example.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.entity.details_Patient;
import com.example.project.entity.patient;

public interface PatientDetail extends JpaRepository<details_Patient, Integer> {
  @Query("SELECT u FROM details_Patient u where detailsPatientId=?1")
  Optional<details_Patient> findPatientDetailById(int patientDetailid);

  @Query("SELECT u FROM details_Patient u where patient=?1")
  Optional<details_Patient> findByPatientId(patient patientid);

}
