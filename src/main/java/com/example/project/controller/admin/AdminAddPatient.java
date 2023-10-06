package com.example.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/add-patient")
public class AdminAddPatient {
  @GetMapping
  public String page() {
    return "admin/add-patient";
  }
}
