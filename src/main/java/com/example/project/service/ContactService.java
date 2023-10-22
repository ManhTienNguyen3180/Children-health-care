package com.example.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ContactRepository;
import com.example.project.entity.contact;
import com.example.project.entity.patient;

import jakarta.transaction.Transactional;

/**
 * ContactService
 */
@Service
@Transactional
public class ContactService {
  @Autowired
  private ContactRepository contactRepository;

  public Optional<contact> findByPatientId(patient patient) {
    Optional<contact> ps = contactRepository.findByPatient(patient);
    if (ps.isPresent()) {
      return contactRepository.findByPatient(patient);
    }

    return null;
  }

  public void addContact(contact p) {
    Optional<contact> ps = contactRepository.findByPatient(p.getPatient());
    if (ps.isPresent()) {
      throw new IllegalStateException();
    } else {
      contactRepository.save(p);
    }
  }

}