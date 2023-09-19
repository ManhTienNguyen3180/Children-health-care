package com.example.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.service.BlogService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class BlogController {
    
    
    @Autowired 
    private BlogService BlogService;

    @GetMapping("/blog")
    public String getBlog(Model model){
        model.addAttribute("result", BlogService.fetchBLogList());
        return "blog";
    }

    @RequestMapping("/blog-detail/{blog_id}")
    public String getBlogById(@RequestParam(value="blog_id") int id, Model model) {
        model.addAttribute("blog", BlogService.findBlogById(id)) ;
        return "blog-detail";
    }
    
    
}

