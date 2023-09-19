package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BlogDetailController {
    

    @GetMapping("/blog-detail")
    public String blogDetail(){
        return "blog-detail";
    }

    
    

   
}
