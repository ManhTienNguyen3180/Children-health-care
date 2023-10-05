package com.example.project.Admin.DoctorController.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/add-doctor")
public class AdminAddDoctor {
  @GetMapping
  public String AdminAddD() {
    return "admin/add-doctor";
  }
}
