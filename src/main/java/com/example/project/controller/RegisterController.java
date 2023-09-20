package com.example.project.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.user;
import com.example.project.service.UserService;

@Controller
@RequestMapping("/signup")
public class RegisterController {
  @GetMapping
  public String registerpage(Model model) {

    return "signup";
  }

  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public String register(
      @RequestParam("uname") String username, // Use @RequestParam to capture form data
      @RequestParam("fullname") String fullname,
      @RequestParam("Gender") Integer gender,
      @RequestParam("phonenum") String phonenum,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      Model model) {
    user s = userService.findUserByUserN(username);
    if (s == null) {
      s = userService.findUserByEmail(email);
      if (s == null) {
        user u = new user(gender, username,
            password,
            fullname,
            gender,
            Integer.parseInt(phonenum),
            email,
            password,
            1,
            1,
            username, LocalDate.now(),
            "user");
        userService.addNewUser(u);

      } else {
        model.addAttribute("mEmail", "email already used");
        return "signup";
      }

    } else {
      model.addAttribute("mUsername", "username already used");
      return "signup";
    }

    return "redirect:/login";
  }

}
