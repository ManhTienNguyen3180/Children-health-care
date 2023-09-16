package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project.service.DoctorService;

@Controller
public class DoctorController {
    
    @Autowired 
    private DoctorService DoctorService;

    @GetMapping("/doctor")
    public String getDoctor(Model model){
        model.addAttribute("result", DoctorService.fetchDoctorList());
        return "doctor";
    }
}
