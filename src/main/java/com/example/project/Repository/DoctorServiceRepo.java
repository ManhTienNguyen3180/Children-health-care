package com.example.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.doctorservice;

@Repository
public interface DoctorServiceRepo extends JpaRepository<doctorservice, Integer>{
    @Query("SELECT ds FROM doctorservice ds WHERE doctorID = ?1")
      Optional<doctorservice> findDoctorByDocID(int docID);

      @Query("SELECT ds FROM doctorservice ds WHERE doctorID = ?1")
      List<doctorservice> findDoctorByDoctorID(int docID);
      
}
