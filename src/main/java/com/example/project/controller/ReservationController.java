package com.example.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.reservation;
import com.example.project.entity.service;
import com.example.project.service.DoctorService;
import com.example.project.service.ReservationService;
import com.example.project.service.ServiceService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {
    @Autowired 
    ServiceService ServiceService;

    @Autowired 
    DoctorService DoctorService;

    @Autowired 
    ReservationService ReservationService;

    @GetMapping("bookingappointment")
    public String getData(Model model, HttpSession session){
        model.addAttribute("listSelected", session.getAttribute("selectedService"));

        model.addAttribute("listService", ServiceService.fechServicesList());  
        model.addAttribute("listDoctor", DoctorService.fetchDoctorList());  
        
        return "bookingappointment";
    }

    //post mapping get path variable from url and save into reservation table
    @PostMapping("/bookingappointment/save")
    public String saveReservation(@RequestParam("patient_name") String patient_name,
                                @RequestParam("service_id") int service_id,
                                @RequestParam("doctor_name") String doctor_name,Model model){
        reservation reservation = new reservation();
        Optional<service> service = ServiceService.findServiceById(service_id);
        service s = service.get();
        reservation.setPatient_name(patient_name);
        reservation.setService_name(s.getService_name());
        reservation.setService_id(service_id);
        reservation.setDoctor_name(doctor_name);
        reservation.setStatus(1);
        reservation.setCreate_by("admin");
        reservation.setCreate_at(new java.sql.Date(System.currentTimeMillis()));
        reservation.setActual_date(new java.sql.Date(System.currentTimeMillis()));
        reservation.setPatient_id(1);
        reservation.setPrice(s.getPrice());
        model.addAttribute("message", "Reservation saved successfully!");
        ReservationService.save(reservation);
        
        return "redirect:/bookingappointment";
    }


    

}
