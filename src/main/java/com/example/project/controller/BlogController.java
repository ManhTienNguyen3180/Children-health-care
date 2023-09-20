package com.example.project.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.entity.blog;
import com.example.project.service.BlogService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class BlogController {
    
    
    @Autowired 
    private BlogService BlogService;

    
    @GetMapping("/blog")
    public String getBlog(Model model){
        model.addAttribute("result", BlogService.fetchBLogList());
        return "blog";
    }

    // @RequestMapping("/blog-detail/{blog_id}")  
    // public String getBlogById(@RequestParam(value="blog_id") String bid, Model model) {
    //     int id=Integer.parseInt(bid);
    //     model.addAttribute("blog", BlogService.findBlogById(id)) ;
    //     return "blog-detail";
    // }
    
    @GetMapping("/blog-detail/{id}")
    public String viewBlogDetail(@PathVariable int id, Model model) {
        
        model.addAttribute("blog", BlogService.findBlogById(id).orElse(null));
        return "blog-detail";
    }
    
    // @GetMapping("/blog-detail")
    // public String blogDetail(){
    //     return "blog-detail";
    // }

}


