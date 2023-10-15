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
    


    

}
