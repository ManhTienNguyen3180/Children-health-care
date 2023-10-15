package com.example.project.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.project.Repository.DoctorRepo;
import com.example.project.Repository.DoctorServiceRepo;
import com.example.project.Repository.SlotRepo;
import com.example.project.entity.doctor;
import com.example.project.entity.doctorservice;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo repo;
    @Autowired
    private DoctorServiceRepo repo2;
    @Autowired
    private SlotRepo SlotRepo;

    @Override
    public List<doctor> fetchDoctorList() {
        return (List<doctor>) repo.findAll();
    }

    @Override
    public List<doctorservice> fetchDoctorservicesList() {
        return (List<doctorservice>) repo2.findAll();
    }

    @Override
    public Optional<doctor> findDoctorById(int id) {
        
        return (Optional<doctor>) repo.findById(id);
    }

    @Override
    public void save(doctor doctor) {
        repo.save(doctor);

    }

    @Override
    @Modifying
    public void savedocservice(doctorservice doctorservice) {
        repo2.save(doctorservice);
    }

    @Override
    public Page<doctor> findPaginatedDoctor(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if(keyword != null) {
            return repo.search(keyword, pageable);
        }
        return repo.findAll(pageable);
    }

    @Override
    public void deleteDoctor(int id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteDoctorService(int id) {
        repo2.deleteById(id);
    }

    @Override
    public void deleteSlot(int id) {
        SlotRepo.deleteById(id);
    }

    @Override
    public doctor findDoctorByDoctorName(String doctor_name) {

        Optional<doctor> useOptional = repo.findDoctorByName(doctor_name);
        if (useOptional.isPresent()) {
            doctor u = useOptional.get();
            return u;
        }
        return null;

    }

    @Override
    public doctorservice findDoctorByDocID(int docID) {
        Optional<doctorservice> useOptional = repo2.findDoctorByDocID(docID);
        if (useOptional.isPresent()) {
            doctorservice u = useOptional.get();
            return u;
        }
        return null;
    }

    @Override
    public doctor findById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<doctorservice> finDoctorservicesByDoctorID(int id) {
        return (List<doctorservice>) repo2.findDoctorByDoctorID(id);
    }


}

    

    


