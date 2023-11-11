package com.example.project.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project.Repository.PatientDetail;
import com.example.project.Repository.PatientRepo;
import com.example.project.dto.reservationDTO;
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

  public Page<patient> FindPaginatedByDoctor(String search, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    Page<Object[]> page = patientRepo.findAllD(search, pageable);
    List<Object[]> listB = page.getContent();
    List<patient> listp = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    for (Object[] b : listB) {
      patient p = new patient();
      p.setPatient_id(Integer.parseInt(String.valueOf(b[0])));
      p.setDob(Date.valueOf(LocalDate.parse(String.valueOf(b[1]), formatter)));
      p.setCreate_at(Date.valueOf(LocalDate.parse(String.valueOf(b[2]), formatter)));
      p.setCreate_by(String.valueOf(b[3]));
      p.setDescription(String.valueOf(b[4]));
      p.setGender(Integer.parseInt(String.valueOf(b[5])));
      p.setPatient_name(String.valueOf(b[7]));
      p.setStatus(Integer.parseInt(String.valueOf(b[8])));
      try {
        p.setUser_id(Integer.parseInt(String.valueOf(b[9])));

      } catch (Exception e) {
        // TODO: handle exception
      }
      p.setPatient_email(String.valueOf(b[10]));
      p.setPatient_phone(String.valueOf(b[11]));
      p.setPatient_address(String.valueOf(b[12]));
      listp.add(p);
    }
    return new PageImpl<>(listp, pageable, page.getTotalElements());
  }

  public Page<patient> findUsersAndFilterStatus(int r, Integer pageno, int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);

    return patientRepo.findUsersAndFilterStatus(r, pageable);
  }

  public Page<patient> findUsersAndFilterStatusbyDoctor(String doctorname, int r, Integer pageno, int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    Page<Object[]> page = patientRepo.findUsersAndFilterStatusbyDoctor(doctorname, r, pageable);
    List<Object[]> listB = page.getContent();
    List<patient> listp = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    for (Object[] b : listB) {
      patient p = new patient();
      p.setPatient_id(Integer.parseInt(String.valueOf(b[0])));
      p.setDob(Date.valueOf(LocalDate.parse(String.valueOf(b[1]), formatter)));
      p.setCreate_at(Date.valueOf(LocalDate.parse(String.valueOf(b[2]), formatter)));
      p.setCreate_by(String.valueOf(b[3]));
      p.setDescription(String.valueOf(b[4]));
      p.setGender(Integer.parseInt(String.valueOf(b[5])));
      p.setImage(String.valueOf(b[6]));
      p.setPatient_name(String.valueOf(b[7]));
      p.setStatus(Integer.parseInt(String.valueOf(b[8])));
      p.setUser_id(Integer.parseInt(String.valueOf(b[9])));
      p.setPatient_email(String.valueOf(b[10]));
      p.setPatient_phone(String.valueOf(b[11]));
      p.setPatient_address(String.valueOf(b[12]));
      listp.add(p);
    }
    return new PageImpl<>(listp, pageable, page.getTotalElements());
  }

  public Page<patient> findUsersAndFilterGender(int r, Integer pageno, int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    return patientRepo.findUsersAndFilterGender(r, pageable);
  }

  public Page<patient> findUsersAndFilterGenderByDoctor(String doctorname, int r, Integer pageno, int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    Page<Object[]> page = patientRepo.findUsersAndFilterGenderbyDoctor(doctorname, r, pageable);
    List<Object[]> listB = page.getContent();
    List<patient> listp = new ArrayList<>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    for (Object[] b : listB) {
      patient p = new patient();
      p.setPatient_id(Integer.parseInt(String.valueOf(b[0])));
      p.setDob(Date.valueOf(LocalDate.parse(String.valueOf(b[1]), formatter)));
      p.setCreate_at(Date.valueOf(LocalDate.parse(String.valueOf(b[2]), formatter)));
      p.setCreate_by(String.valueOf(b[3]));
      p.setDescription(String.valueOf(b[4]));
      p.setGender(Integer.parseInt(String.valueOf(b[5])));
      p.setImage(String.valueOf(b[6]));
      p.setPatient_name(String.valueOf(b[7]));
      p.setStatus(Integer.parseInt(String.valueOf(b[8])));
      p.setUser_id(Integer.parseInt(String.valueOf(b[9])));
      p.setPatient_email(String.valueOf(b[10]));
      p.setPatient_phone(String.valueOf(b[11]));
      p.setPatient_address(String.valueOf(b[12]));
      listp.add(p);
    }
    return new PageImpl<>(listp, pageable, page.getTotalElements());
  }

  public Page<patient> findPaginatedContainsWithPagingByDoctor(String doctorname, String searchValue, Integer pageno,
      int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    Page<Object[]> page = patientRepo.findPaginatedContainsWithPagingbyDoctor(doctorname, searchValue, pageable);
    List<Object[]> listB = page.getContent();
    List<patient> listp = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    for (Object[] b : listB) {
      patient p = new patient();
      p.setPatient_id(Integer.parseInt(String.valueOf(b[0])));
      p.setDob(Date.valueOf(LocalDate.parse(String.valueOf(b[1]), formatter)));
      p.setCreate_at(Date.valueOf(LocalDate.parse(String.valueOf(b[2]), formatter)));
      p.setCreate_by(String.valueOf(b[3]));
      p.setDescription(String.valueOf(b[4]));
      p.setGender(Integer.parseInt(String.valueOf(b[5])));
      p.setImage(String.valueOf(b[6]));
      p.setPatient_name(String.valueOf(b[7]));
      p.setStatus(Integer.parseInt(String.valueOf(b[8])));
      p.setUser_id(Integer.parseInt(String.valueOf(b[9])));
      p.setPatient_email(String.valueOf(b[10]));
      p.setPatient_phone(String.valueOf(b[11]));
      p.setPatient_address(String.valueOf(b[12]));
      listp.add(p);
    }
    return new PageImpl<>(listp, pageable, page.getTotalElements());
  }

  public Page<patient> findUsersContainsAndFilterStatusWithPagingByDoctor(String doctorname, String searchValue, int r,
      Integer pageno,
      int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    Page<Object[]> page = patientRepo.findUsersContainsAndFilterStatusWithPagingbyDoctor(doctorname, searchValue, r,
        pageable);
    List<Object[]> listB = page.getContent();
    List<patient> listp = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    for (Object[] b : listB) {
      patient p = new patient();
      p.setPatient_id(Integer.parseInt(String.valueOf(b[0])));
      p.setDob(Date.valueOf(LocalDate.parse(String.valueOf(b[1]), formatter)));
      p.setCreate_at(Date.valueOf(LocalDate.parse(String.valueOf(b[2]), formatter)));
      p.setCreate_by(String.valueOf(b[3]));
      p.setDescription(String.valueOf(b[4]));
      p.setGender(Integer.parseInt(String.valueOf(b[5])));
      p.setImage(String.valueOf(b[6]));
      p.setPatient_name(String.valueOf(b[7]));
      p.setStatus(Integer.parseInt(String.valueOf(b[8])));
      p.setUser_id(Integer.parseInt(String.valueOf(b[9])));
      p.setPatient_email(String.valueOf(b[10]));
      p.setPatient_phone(String.valueOf(b[11]));
      p.setPatient_address(String.valueOf(b[12]));
      listp.add(p);
    }
    return new PageImpl<>(listp, pageable, page.getTotalElements());
  }

  public Page<patient> findUsersContainsAndFilterGenderWithPagingByDoctor(String doctorname, String searchValue, int r,
      Integer pageno,
      int pagesize) {
    Pageable pageable = PageRequest.of(pageno - 1, pagesize);
    Page<Object[]> page = patientRepo.findUsersContainsAndFilterGenderWithPagingbyDoctor(doctorname, searchValue, r,
        pageable);
    List<Object[]> listB = page.getContent();
    List<patient> listp = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    for (Object[] b : listB) {
      patient p = new patient();
      p.setPatient_id(Integer.parseInt(String.valueOf(b[0])));
      p.setDob(Date.valueOf(LocalDate.parse(String.valueOf(b[1]), formatter)));
      p.setCreate_at(Date.valueOf(LocalDate.parse(String.valueOf(b[2]), formatter)));
      p.setCreate_by(String.valueOf(b[3]));
      p.setDescription(String.valueOf(b[4]));
      p.setGender(Integer.parseInt(String.valueOf(b[5])));
      p.setImage(String.valueOf(b[6]));
      p.setPatient_name(String.valueOf(b[7]));
      p.setStatus(Integer.parseInt(String.valueOf(b[8])));
      p.setUser_id(Integer.parseInt(String.valueOf(b[9])));
      p.setPatient_email(String.valueOf(b[10]));
      p.setPatient_phone(String.valueOf(b[11]));
      p.setPatient_address(String.valueOf(b[12]));
      listp.add(p);
    }
    return new PageImpl<>(listp, pageable, page.getTotalElements());
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

    if (!findByPatientId(p.getPatient_id()).isPresent()) {
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
    return p;
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

  public Optional<details_Patient> findByPatientDetailId(int patientDetailid) {
    List<details_Patient> results = patientDetailRepo.findPatientDetailById(patientDetailid);
    if (!results.isEmpty()) {
      return Optional.of(results.get(0));
    } else {
      return Optional.empty();
    }
  }

  public Optional<details_Patient> findByPatientIdDetail(patient patientId) {
    List<details_Patient> results = patientDetailRepo.findByPatientId(patientId);
    if (!results.isEmpty()) {
      return Optional.of(results.get(0));
    } else {
      return Optional.empty();
    }
  }

  public Optional<details_Patient> findByPatientIdHadReserId(patient patientId, int reservation_id) {
    return patientDetailRepo.findByPatientIdHadReserId(patientId, reservation_id);
  }

  public void addPatient(details_Patient p, int reservation_id) {
    Optional<details_Patient> existingPatient = findByPatientIdDetail(p.getPatient());
    if (existingPatient.isPresent() && reservation_id == 0) {
      details_Patient patientToUpdate = existingPatient.get();
      // update pation detail
      // Set the new details for the patient
      patientToUpdate.setFamily_medical_history(p.getFamily_medical_history());
      patientToUpdate.setMedical_history(p.getMedical_history());
      patientToUpdate.setCreate_at(p.getCreate_at());
      patientToUpdate.setHeartbeat(p.getHeartbeat());
      patientToUpdate.setBody_temperature(p.getBody_temperature());
      patientToUpdate.setBlood(p.getBlood());
      patientToUpdate.setHeight(p.getHeight());
      patientToUpdate.setWeight(p.getWeight());
      patientToUpdate.setBMI(p.getBMI());
      patientToUpdate.setHemoglobin(p.getHemoglobin());
      patientToUpdate.setLefteye(p.getLefteye());
      patientToUpdate.setRighteye(p.getRighteye());
      patientToUpdate.setIOP(p.getIOP());
      patientToUpdate.setDescription(p.getDescription());
      patientToUpdate.setHeal_description(p.getHeal_description());
      patientToUpdate.setEyes_description(p.getEyes_description());
      patientToUpdate.setLefteye_description(p.getLefteye_description());
      patientToUpdate.setRighteye_description(p.getRighteye_description());
      p.setDoctor_id(p.getDoctor_id());
      // Update other properties as needed

    } else if (existingPatient.isPresent() && reservation_id != 0) {
      //
      p.setReservation_id(reservation_id);
      patientDetailRepo.save(p);
    } else if (!existingPatient.isPresent() && reservation_id != 0) {
      patientDetailRepo.save(p);
    }
  }

  public void deletePatient(int id) {
    patientRepo.deleteById(id);
  }

  public void savePantient(int gender, String name, String email, String phone, int id) {
    patientRepo.savePatientChange(gender, name, email, phone, id);
  }

}
