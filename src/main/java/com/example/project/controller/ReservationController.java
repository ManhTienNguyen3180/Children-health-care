package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project.service.DoctorService;
import com.example.project.service.ReservationService;
import com.example.project.service.ServiceService;

@Controller
public class ReservationController {
    @Autowired 
    ServiceService ServiceService;

    @Autowired 
    DoctorService DoctorService;

    @Autowired 
    ReservationService ReservationService;

    @GetMapping("bookingappointment")
    public String getData(Model model){
        model.addAttribute("listService", ServiceService.fechServicesList());  
        model.addAttribute("listDoctor", DoctorService.fetchDoctorList());  
        
        return "bookingappointment";
    }

    

}
