package com.example.project.Admin;

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
