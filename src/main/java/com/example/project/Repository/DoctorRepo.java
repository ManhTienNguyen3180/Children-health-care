package com.example.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.doctor;



@Repository
public interface DoctorRepo extends JpaRepository<doctor, Integer>{
    
    @Query("SELECT d FROM doctor d WHERE doctor_name=?1 ")
      Optional<doctor> findDoctorByName(String doctor_name);
    
      @Query("SELECT p FROM doctor p WHERE CONCAT(p.doctor_name, p.position, p.phone) LIKE %?1%")
      public Page<doctor> search(String keyword, Pageable pageable);

    

}
