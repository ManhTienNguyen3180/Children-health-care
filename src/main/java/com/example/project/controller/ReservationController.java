package com.example.project.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.reservation;
import com.example.project.entity.service;
import com.example.project.service.DoctorService;
import com.example.project.service.ReservationService;
import com.example.project.service.ServiceService;

import jakarta.websocket.server.PathParam;

@Controller

public class ReservationController {
    @Autowired ServiceService ServiceService;
    @Autowired DoctorService DoctorService;
    @Autowired ReservationService ReservationService;

    @GetMapping("bookingappointment")
    public String getData(Model model){
        model.addAttribute("listService", ServiceService.fechServicesList());  
        model.addAttribute("listDoctor", DoctorService.fetchDoctorList());  
        
        return "bookingappointment";
    }

    @PostMapping("bookingappointment/save")
    public String saveReservation(@PathParam("servicename") String servicename) {
        reservation reservation = new reservation();
        java.sql.Date createdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());       
        reservation s = ReservationService.findServiceByService_Name1(servicename);
        int serviceid = s.getService_id();
        int panid = 1;
        int price = s.getPrice();
        int numperson = 1;
        int status = 0;
        reservation.setService_id(serviceid);
        reservation.setPatient_id(panid);
        reservation.setPrice(price);
        reservation.setTotal_cost(price);
        reservation.setNum_person(numperson);
        reservation.setActual_date(createdate);
        reservation.setCreate_at(createdate);
        reservation.setStatus(status);
        ReservationService.save(reservation);
        return "redirect:/bookingappointment";
    }

}
