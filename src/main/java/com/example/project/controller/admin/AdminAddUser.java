package com.example.project.controller.admin;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.role;
import com.example.project.entity.user;

import com.example.project.service.RoleService;
import com.example.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/add-user")
public class AdminAddUser {
  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @GetMapping
  public String page(Model model) {
    model.addAttribute("listrole", roleService.getRole());
    return "admin/add-user";
  }

  @PostMapping
  public String register(
      @RequestParam("uname") String username, // Use @RequestParam to capture form data
      @RequestParam("fullname") String fullname,
      @RequestParam("gender") Integer gender,
      @RequestParam("phonenum") String phonenum,
      @RequestParam("email") String email,
      @RequestParam("password") String password,
      @RequestParam("cpassword") String cpassword,
      @RequestParam("role") String roleid,
      Model model, HttpSession session) {
    // filter
    model.addAttribute("listrole", roleService.getRole());
    // Define your regex patterns
    String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    String phonePattern = "^0[1-9]\\d{7,8}$";
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    user us = (user) session.getAttribute("user");
    user s = userService.findUserByUserN(username);
    user a = userService.findUserByEmail(email);
    role roles = roleService.findRoleById(Integer.parseInt(roleid.trim()));
    if (s == null && a == null) {
      user u = new user();
      u.setUsername(username);
      u.setPassword(passwordEncoder.encode(password));
      u.setFull_name(fullname);
      u.setGender(gender);
      u.setPhone(Integer.parseInt(phonenum));
      u.setEmail(email);
      u.setImage("https://th.bing.com/th/id/R.5097b0247a92d47178df598b82944f15?rik=GOBuYfESpwbvFA&pid=ImgRaw&r=0");
      u.setStatus(1);
      u.setRole_id(roles);
      u.setCreate_at(LocalDate.now());
      // u.setCreate_by(us.getFull_name());
      u.setCreate_by("Admin");
      u.setRolename(roles.getRole_name());
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
