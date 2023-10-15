package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.project.service.UserService;

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

  
  // @PostMapping
  // public String profilePage(Principal p,Model model,HttpSession session) {
  // String email = p.getName();
  // user user = userService.findUserByEmail(email);
  // session.setAttribute("user", user);
  // return "redirect:/home";
  // }

}