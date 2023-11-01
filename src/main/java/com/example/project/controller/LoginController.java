package com.example.project.controller;

import org.springframework.stereotype.Controller;
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
  public String profilePage(@RequestParam("username") String p, HttpSession session) {
    user user = userService.findUserByEmail(p);
    if (user != null) {
      if (user.getStatus() == 0) {
        session.setAttribute("mess", "User not activation");
        return "redirect:/login";

      }
    }
    return "redirect:/login";
  }

}
