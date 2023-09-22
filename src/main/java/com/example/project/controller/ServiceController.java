package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.project.service.ServiceService;

@Controller
public class ServiceController {

    @Autowired ServiceService ServiceService;

    @GetMapping("/service")
    public String getService(Model model){
        model.addAttribute("result", ServiceService.fetchServiceList());
        return "service";
    }

}
