package com.example.project.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AdminAppointment
 */
@Controller
@RequestMapping("admin/appointment")
public class AdminAppointment {

  @GetMapping
  public String page() {
    return "admin/appointment";
  }
}