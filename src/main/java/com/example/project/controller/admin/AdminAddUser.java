package com.example.project.controller.admin;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.user;

import com.example.project.service.RoleService;
import com.example.project.service.UserService;

@Controller
@RequestMapping("admin/add-user")
public class AdminAddUser {
  @GetMapping
  public String page() {
    return "admin/add-user";
  }

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @PostMapping
  public String register(
      @RequestParam("uname") String username, // Use @RequestParam to capture form data
      @RequestParam("fullname") String fullname,
      @RequestParam("gender") Integer gender,
      @RequestParam("phonenum") String phonenum,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      @RequestParam("cpassword") String cpassword,
      Model model) {
    // filter

    // Define your regex patterns
    String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    String phonePattern = "^0[1-9]\\d{7,8}$";

    if (!password.equalsIgnoreCase(cpassword) || !email.matches(emailPattern)
        || !phonenum.matches(phonePattern) || password.length() < 6) {
      if (!password.equalsIgnoreCase(cpassword)) {
        model.addAttribute("mPassword", "Passwords do not match. Please re-enter.");
      }
      if (password.length() < 6) {
        model.addAttribute("mPasswordValid", "Password must have at least 6 characters.");
      }
      if (!email.matches(emailPattern)) {
        model.addAttribute("mEmail", "Please enter a gmail email. Exp:abc@gmail.com");
      }
      if (!phonenum.matches(phonePattern)) {
        model.addAttribute("mPhone",
            "Please enter a valid phone number starting with '0' and followed by 8 or 9 digits.");
      }
      return "/admin/add-user";
    } 
      user s = userService.findUserByUserN(username);
      user a = userService.findUserByEmail(email);
      if (s == null && a == null) {
        user u = new user(username,
            password,
            fullname,
            gender,
            Integer.parseInt(phonenum),
            email,
            "https://th.bing.com/th/id/R.5097b0247a92d47178df598b82944f15?rik=GOBuYfESpwbvFA&pid=ImgRaw&r=0",
            1,
            roleService.findUserById(1),
            "Admin", LocalDate.now());
        userService.addNewUser2(u);
        model.addAttribute("mess", "Add user successful");
      } else {

        if (s != null) {

          model.addAttribute("mUsername", "username already used");
          model.addAttribute("Gender", gender);
        }
        if (a != null) {
          model.addAttribute("mEmail", "email already used");
          model.addAttribute("Gender", gender);
        }

        return "admin/add-user";
      }
    

    return "/admin/add-user";
  }
}
