package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

<<<<<<< HEAD
    
=======
    @GetMapping("/doctor-detail/{id}")
    public String viewDoctorDetail(@PathVariable int id, Model model) {
        
        model.addAttribute("doc", DoctorService.findDoctorById(id).orElse(null));
        return "doctor-detail";
    }

    // @GetMapping("/doctor-detail")
    // public String doctorDetail(){
    //     return "doctor-detail";
    // }
>>>>>>> f5e8fa03a98a4befeb1220d8d6c2cccd1185c09f
}
