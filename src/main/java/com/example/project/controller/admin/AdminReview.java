package com.example.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/review")
public class AdminReview {
  @GetMapping
  public String AdminAppointment() {
    return "admin/review";
  }
}
