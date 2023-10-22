package com.example.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.entity.contact;
import com.example.project.entity.patient;

import java.util.List;

public interface ContactRepository extends JpaRepository<contact, Integer> {

  @Query("SELECT u FROM contact u WHERE patient= ?1 ")
  Optional<contact> findByPatient(patient patient);
}
