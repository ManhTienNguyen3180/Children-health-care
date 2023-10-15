package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.project.service.DoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DoctorController {
    
    @Autowired 
    private DoctorService DoctorService;

    @GetMapping("/doctor")
    public String getDoctor(Model model){
        model.addAttribute("result", DoctorService.fetchDoctorList());
        return "doctor";
    }

    
    @GetMapping("/doctor-detail/{id}")
    public String viewDoctorDetail(@PathVariable int id, Model model,HttpSession session) {
        session.setAttribute("doctorIds", id);
        model.addAttribute("doc", DoctorService.findDoctorById(id).orElse(null));
        return "doctor-detail";
    }

    
}
