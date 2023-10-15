package com.example.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.project.entity.user;
import com.example.project.service.UserService;

import jakarta.servlet.http.HttpSession;
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

