package com.example.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.patient;

import jakarta.transaction.Transactional;

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


  @Query(value = "SELECT p.patient_id,p.dob,p.create_at,p.create_by,p.description,p.gender,p.image,p.patient_name,p.status,p.user_id,p.patient_email,p.patient_phone,p.patient_address,p.modify_at FROM patient p\n" + //
    "JOIN reservation r ON p.patient_id = r.patient_id where r.doctor_name=?1", nativeQuery = true)
    Page<Object[]> findAllD(String doctorname, Pageable pageable);
  @Query(value = "SELECT p.patient_id,p.dob,p.create_at,p.create_by,p.description,p.gender,p.image,p.patient_name,p.status,p.user_id,p.patient_email,p.patient_phone,p.patient_address,p.modify_at FROM patient p\n" + //
    "JOIN reservation r ON p.patient_id = r.patient_id where r.doctor_name=?1 and p.status=?2", nativeQuery = true)
    Page<Object[]> findUsersAndFilterStatusbyDoctor(String doctorname,int r, Pageable pageable);

  @Query(value = "SELECT p.patient_id,p.dob,p.create_at,p.create_by,p.description,p.gender,p.image,p.patient_name,p.status,p.user_id,p.patient_email,p.patient_phone,p.patient_address,p.modify_at FROM patient p\n" + //
    "JOIN reservation r ON p.patient_id = r.patient_id where r.doctor_name=?1 and p.gender=?2", nativeQuery = true)
    Page<Object[]> findUsersAndFilterGenderbyDoctor(String doctorname,int r, Pageable pageable);
  
    @Query(value = "SELECT p.patient_id,p.dob,p.create_at,p.create_by,p.description,p.gender,p.image,p.patient_name,p.status,p.user_id,p.patient_email,p.patient_phone,p.patient_address,p.modify_at FROM patient p\n" + //
    "JOIN reservation r ON p.patient_id = r.patient_id where r.doctor_name=?1 and p.patient_name LIKE  %?2% or p.patient_email LIKE  %?2% ", nativeQuery = true)
    Page<Object[]> findPaginatedContainsWithPagingbyDoctor(String doctorname,String search, Pageable pageable);
  
    @Query(value = "SELECT p.patient_id,p.dob,p.create_at,p.create_by,p.description,p.gender,p.image,p.patient_name,p.status,p.user_id,p.patient_email,p.patient_phone,p.patient_address,p.modify_at FROM patient p\n" + //
    "JOIN reservation r ON p.patient_id = r.patient_id where r.doctor_name=?1 and p.gender=?3 and p.patient_name LIKE  %?2% or p.patient_email LIKE  %?2%  ", nativeQuery = true)
    Page<Object[]> findUsersContainsAndFilterGenderWithPagingbyDoctor(String doctorname,String search,int r, Pageable pageable);
  @Query(value = "SELECT p.patient_id,p.dob,p.create_at,p.create_by,p.description,p.gender,p.image,p.patient_name,p.status,p.user_id,p.patient_email,p.patient_phone,p.patient_address,p.modify_at FROM patient p\n" + //
    "JOIN reservation r ON p.patient_id = r.patient_id where r.doctor_name=?1 and p.status=?3 and p.patient_name LIKE  %?2% or p.patient_email LIKE  %?2%  ", nativeQuery = true)
    Page<Object[]> findUsersContainsAndFilterStatusWithPagingbyDoctor(String doctorname,String search,int r, Pageable pageable);
  
    @Query("SELECT u FROM patient u where patient_id=?1")
  Optional<patient> findByPatient_id(int patient_id);

  @Query("SELECT u FROM patient u where user_id=?1")
  List<patient> findByUser_id(int patient_id);

  @Query("SELECT u FROM patient u where patient_email=?1")
  Optional<patient> findbyEmail(String patient_email);



  @Modifying
  @Transactional
  @Query(value = "UPDATE patient SET `gender` = ?1, `patient_name` = ?2, `patient_email` = ?3, `patient_phone` = ?4 WHERE patient_id = ?5", nativeQuery = true)
  void savePatientChange(int gender, String name, String email, String phone, int id);
}