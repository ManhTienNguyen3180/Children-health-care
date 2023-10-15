package com.example.project.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.user;
import com.example.project.service.UserService;

@Controller
public class RegisterController {
  
  
  @GetMapping("/signup")
  public String registerpage(Model model) {

    return "signup";
  }

  
  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping("/saveUser")
  public String register(
      @RequestParam(name = "uname") String username, // Use @RequestParam to capture form data
      @RequestParam(name= "fullname") String fullname,
      @RequestParam(name = "Gender") Integer gender,
      @RequestParam(name = "phonenum") String phonenum,
      @RequestParam(name = "email") String email,
      @RequestParam(name = "password") String password,
      Model model) {
    user s = userService.findUserByEmail(email);
    if (s == null) {
        user u = new user(username,
            password,
            fullname,
            gender,
            Integer.parseInt(phonenum),
            email,
            "abc",
            0,
            "user",LocalDate.now(), "ROLE_USER"
            );
            System.out.println(u.toString());
        userService.save(u);

       
      // } else {
      //   model.addAttribute("message", "Email already exists");
      //   return "signup";
      }
      return "login";
  }

}
