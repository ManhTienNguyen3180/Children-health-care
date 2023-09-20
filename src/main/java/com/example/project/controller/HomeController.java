package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.project.service.DoctorService;
import com.example.project.service.ServiceService;

@Controller
public class HomeController {
    
    
    @Autowired 
    private DoctorService DoctorService;
    @Autowired 
    private ServiceService ServiceService;
    
    @GetMapping("/")
    public String home1(){
        return "home";
    }
    
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("doctor", DoctorService.fetchDoctorList());
        model.addAttribute("service", ServiceService.fechServicesList());
        return "home";
    }
    
    
    

}
