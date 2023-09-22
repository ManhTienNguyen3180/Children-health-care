package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import com.example.project.service.BlogService;
import com.example.project.service.DoctorService;
import com.example.project.service.ServiceService;

@Controller
public class HomeController {
    
    
    @Autowired 
    private DoctorService DoctorService;
    @Autowired 
    private ServiceService ServiceService;
    @Autowired 
    private BlogService BlogService;

    @GetMapping("/")
    public String home1(){
        return "home";
    }
    @GetMapping("/user-profile")
    public String userinfor(){
        return "user-profile";
    }
    
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("doctor", DoctorService.fetchDoctorList());
        model.addAttribute("service", ServiceService.fechServicesList());
        model.addAttribute("blogNew", BlogService.getBlogsNew());
        return "home";
    }
    
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }
        
    
}
