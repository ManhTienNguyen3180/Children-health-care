package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.project.service.DoctorService;
import com.example.project.service.ServiceService;

@Controller
public class HomeController {
    
    // @GetMapping("/home")
    // public String home(){
    //     return "home";
    // }
    @Autowired 
    private DoctorService DoctorService;
    @Autowired 
    private ServiceService ServiceService;
    
    
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("doctor", DoctorService.fetchDoctorList());
        model.addAttribute("service", ServiceService.fechServicesList());
        return "home";
    }
    
}
