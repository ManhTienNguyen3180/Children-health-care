package com.example.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project.Repository.PatientRepo;
import com.example.project.entity.patient;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientService {
  @Autowired
  private PatientRepo patientRepo;

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
    try {
      patientRepo.save(p);
    } catch (Exception e) {
      throw new IllegalStateException();
    }

  }

  public void updatePatientBypatientId(int patientId, patient p) {
    Optional<patient> patientOptional = patientRepo
        .findById(patientId);
    if (patientOptional.isPresent()) {
      patient foundUser = patientOptional.get();
      foundUser.setCreate_at(p.getCreate_at());
      foundUser.setCreate_by(p.getCreate_by());
      foundUser.setDescription(p.getDescription());
      foundUser.setDob(p.getDob());
      foundUser.setGender(p.getGender());
      foundUser.setImage(p.getImage());
      foundUser.setPatient_address(p.getPatient_address());
      foundUser.setPatient_email(p.getPatient_email());
      foundUser.setPatient_name(p.getPatient_name());
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

  public void save(patient patient) {
    patientRepo.save(patient);
  }

  // select last patient id from database by query mysql
  public int getLastPatientId() {
    return patientRepo.getLastPatientId();
  }
}
