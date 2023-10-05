package com.example.project.Admin.BlogController.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/blogs")
public class AdminBlogs {
  @GetMapping
  public String page() {
    return "admin/blogs";
  }
}
