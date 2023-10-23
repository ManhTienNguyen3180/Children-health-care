package com.example.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.patient;

@Repository
public interface PatientRepo extends JpaRepository<patient, Integer> {
  @Query(value = "SELECT MAX(patient_id) FROM patient", nativeQuery = true)
  int getLastPatientId();

  @Query("SELECT u FROM patient u")
  List<patient> findAll();

  // pageable
  Page<patient> findAll(Pageable pageable);

  @Query("SELECT u FROM patient u where status=?1")
  Page<patient> findUsersAndFilterStatus(int r, Pageable pageable);

  @Query("SELECT u FROM patient u where gender=?1")
  public Page<patient> findUsersAndFilterGender(int r, Pageable pageable);

  @Query("SELECT u FROM patient u WHERE u.patient_name LIKE  %?1% or u.patient_email LIKE  %?1%  ")
  Page<patient> findPaginatedContainsWithPaging(String search, Pageable pageable);

  @Query("SELECT u FROM patient u WHERE u.patient_name LIKE  %?1% or u.patient_email LIKE  %?1% AND u.status=?2 ")
  Page<patient> findUsersContainsAndFilterStatusWithPaging(String name, int status, Pageable pageable);

  @Query("SELECT u FROM patient u WHERE u.patient_name LIKE  %?1% or u.patient_email LIKE  %?1% AND gender= ?2 ")
  Page<patient> findUsersContainsAndFilterGenderWithPaging(String name, int gender, Pageable pageable);

  @Query("SELECT u FROM patient u where patient_id=?1")
  Optional<patient> findByPatient_id(int patient_id);

  @Query("SELECT u FROM patient u where patient_email=?1")
  Optional<patient> findbyEmail(String patient_email);

  
}