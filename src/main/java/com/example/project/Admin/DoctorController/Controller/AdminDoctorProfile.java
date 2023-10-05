package com.example.project.Admin.DoctorController.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/dr-profile")
public class AdminDoctorProfile {
  @GetMapping
  public String page() {
    return "admin/dr-profile";
  }
}
