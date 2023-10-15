package com.example.project.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.service;

@Repository
public interface ServiceRepo extends JpaRepository<service, Integer>{
    @Query("Select s from service s where s.service_id IN ?1")
    List<service> findByServiceId(List<Integer> serviceIds); 
    
    @Query("Select s from service s where s.service_id NOT IN ?1")
    List<service> findNotByServiceId(List<Integer> serviceIds); 
}
