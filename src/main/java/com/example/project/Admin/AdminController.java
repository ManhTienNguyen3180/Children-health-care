package com.example.project.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/dashboard")
public class AdminController {
 
  @GetMapping
  public String page(){
    return "admin/index";
  }
}
