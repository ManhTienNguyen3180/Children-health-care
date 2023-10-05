package com.example.project.Admin.PatientController.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/patient-profile")
public class AdminPatientProfile {
  @GetMapping
  public String page() {
    return "admin/patient-profile";
  }
}
