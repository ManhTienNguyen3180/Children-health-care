package com.example.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/patientDashboard")
public class AdminDashboardPatient {
  @GetMapping
  public String page() {
    return "admin/patient-dashboard";
  }

  @PostMapping
  public String search(@RequestParam(value="email",required = false) String email,
      @RequestParam(value = "namep",required = false) String name) {

    if (!email.isEmpty()) {
      return "redirect:/admin/patientsSearch?s="+email;
    }
    if (!name.isEmpty()) {
      return "redirect:/admin/patientsSearch?s="+name;
    }
    return "admin/patient-dashboard";
  }
  

}
