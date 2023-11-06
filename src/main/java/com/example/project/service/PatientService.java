package com.example.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project.Repository.PatientDetail;
import com.example.project.Repository.PatientRepo;
import com.example.project.entity.details_Patient;
import com.example.project.entity.patient;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientService {
  @Autowired
  private PatientRepo patientRepo;
  @Autowired
  private PatientDetail patientDetailRepo;

  public List<patient> getPatient() {
    return patientRepo.findAll();
  }

  public Page<patient> FindPaginated(int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return patientRepo.findAll(pageable);
  }

  public Page<patient> findUsersAndFilterStatus(int r, Integer pageno, int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    return patientRepo.findUsersAndFilterStatus(r, pageable);
  }

  public Page<patient> findUsersAndFilterGender(int r, Integer pageno, int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    return patientRepo.findUsersAndFilterGender(r, pageable);
  }

  public Page<patient> findPaginatedContainsWithPaging(String searchValue, Integer pageno, int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    return patientRepo.findPaginatedContainsWithPaging(searchValue, pageable);
  }

  public Page<patient> findUsersContainsAndFilterStatusWithPaging(String searchValue, int r, Integer pageno,
      int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    return patientRepo.findUsersContainsAndFilterStatusWithPaging(searchValue, r, pageable);
  }

  public Page<patient> findUsersContainsAndFilterGenderWithPaging(String searchValue, int r, Integer pageno,
      int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    return patientRepo.findUsersContainsAndFilterGenderWithPaging(searchValue, r, pageable);
  }

  public void addPatient(patient p) {

    if (findByPatientId(p.getPatient_id()) == null) {
      // If the patient with the given ID doesn't exist, save the new patient
      save(p);
    } else {
      // If the patient with the given ID already exists, you can choose to handle it
      // differently, such as throwing an exception or logging a message
      throw new IllegalStateException("Patient with the same ID already exists.");
    }
  }

  public patient findByPatientEmail(String patient_email) {
    if (patientRepo.findbyEmail(patient_email).isPresent()) {
      return patientRepo.findbyEmail(patient_email).get();
    } else {
      return null;
    }
  }

  public void updatePatientBypatientId(int patientId, patient p) {
    Optional<patient> patientOptional = patientRepo
        .findById(patientId);
    if (patientOptional.isPresent()) {
      patient foundUser = patientOptional.get();

      foundUser.setPatient_address(p.getPatient_address());
      foundUser.setPatient_email(p.getPatient_email());

      foundUser.setPatient_phone(p.getPatient_phone());
      foundUser.setStatus(p.getStatus());
      foundUser.setModify_at(LocalDate.now());
      patientRepo.save(foundUser);
    } else {
      // Handle the case where the user is not found
      throw new EntityNotFoundException("Patient not found with ID: " + patientId);
    }
  }

  public Optional<patient> findByPatientId(int patientId) {
    Optional<patient> p = patientRepo.findByPatient_id(patientId);
    if (p.isPresent()) {
      return p;
    } else {
      return null;
    }
  }
public List<patient> findByUserId(int user_id) {
    List<patient> p = patientRepo.findByUser_id(user_id);
    if (!p.isEmpty()) {
      return p;
    } else {
      return null;
    }
  }
  public void save(patient patient) {
    patientRepo.save(patient);
  }

  // select last patient id from database by query mysql
  public int getLastPatientId() {
    return patientRepo.getLastPatientId();
  }
  // Detail patient

  public Optional<details_Patient> findByPatientDetailId(int patientid) {
    if (patientDetailRepo.findPatientDetailById(patientid).isPresent()) {
      return patientDetailRepo.findPatientDetailById(patientid);
    }
    return null;
  }

  public Optional<details_Patient> findByPatientIdDetail(patient patientid) {
    if (patientDetailRepo.findByPatientId(patientid).isPresent()) {
      return patientDetailRepo.findByPatientId(patientid);
    }
    return null;
  }

  public void addPatient(details_Patient p) {

    if (findByPatientDetailId(p.getDetailsPatientId()) == null) {
      // If the patient with the given ID doesn't exist, save the new patient
      patientDetailRepo.save(p);
    } else {
      // If the patient with the given ID already exists, you can choose to handle it
      // differently, such as throwing an exception or logging a message
      throw new IllegalStateException("Patient with the same ID already exists.");
    }
  }

  public void deletePatient(int id) {
    patientRepo.deleteById(id);
  }

  public void savePantient(int gender, String name, String email, String phone, int id) {
    patientRepo.savePatientChange(gender, name, email, phone, id);
  }
  
}
