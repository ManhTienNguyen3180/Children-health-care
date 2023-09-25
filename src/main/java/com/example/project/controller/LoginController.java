package com.example.project.controller;


import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.entity.user;
import com.example.project.service.UserService;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpSession;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
/**
 * LoginController
 */
@Controller
@RequestMapping("/login")
public class LoginController {
  private final UserService userService;
  
  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String loginform() {

    return "login";
  }

  @PostMapping
  public String login(Model model,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      HttpSession session) {
    user s = userService.findUserByEmail(email);
    if (s != null) {

      if (s.getPassword().equalsIgnoreCase(password)) {
        session.setAttribute("user", s);
        return "redirect:/home";
      } else {
        model.addAttribute("message", " wrong password");
      }
    }else{
       model.addAttribute("message", " Email is not exist");
    }
    return "login";
  }

  



}