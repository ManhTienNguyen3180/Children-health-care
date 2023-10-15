package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.project.entity.doctor;
import com.example.project.entity.doctorservice;

public interface DoctorService {
    List<doctor> fetchDoctorList();

    List<doctorservice> fetchDoctorservicesList();

    Optional<doctor> findDoctorById(int id);

    public void save(doctor doctor);

    public void savedocservice(doctorservice doctorservice);

    Page<doctor> findPaginatedDoctor(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);

    void deleteDoctor(int id);

    void deleteDoctorService(int id);

    void deleteSlot(int id);

    public doctor findDoctorByDoctorName(String doctor_name);

    public doctorservice findDoctorByDocID(int docID);

    public doctor findById(int id);

    List<doctorservice> finDoctorservicesByDoctorID(int id);

    Page<doctor> getAllDoc(int page, int size);

    Page<doctor> getDocByService(int id, int page, int size);

    List<Object> getSerByDoc(int id);

    List<Object> getSlotByDoc(int id);

    List<Object> getDocReview(int id);

    public doctor findLatestDoctor();
}