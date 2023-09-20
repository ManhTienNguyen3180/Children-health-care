package com.example.project.controller;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/home")
    public String home1(){
        return "home";
    }
    @GetMapping("/user-profile")
    public String userinfor(){
        return "user-profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }
        
    
}
