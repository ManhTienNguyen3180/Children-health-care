package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.project.service.ServiceService;

import com.example.project.service.ServiceService;

@Controller
public class ServiceController {   

    // @GetMapping("/service")
    // public String service(){
    //     return "service";
    // }

    @Autowired 
    private ServiceService ServiceService;

    @GetMapping("/service")
    public String getService(Model model){
        model.addAttribute("result", ServiceService.fechServicesList());
        return "service";
    }

    @GetMapping("/service-detail/{id}")
    public String viewServiceDetail(@PathVariable int id, Model model) {
        
        model.addAttribute("service", ServiceService.findServiceById(id).orElse(null));
        return "service-detail";
    }

}
