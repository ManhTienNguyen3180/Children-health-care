package com.example.project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.user;
import com.example.project.entity.token.ConfirmationTokenService;
import com.example.project.service.RoleService;
import com.example.project.service.UserService;

@Controller
@RequestMapping("/signup")
public class RegisterController {
  
  
  @GetMapping
  public String registerpage(Model model) {

    return "signup";
  }

  
  private final UserService userService;

  @Autowired
  private ConfirmationTokenService cTokenService;
  @Autowired
  private RoleService roleService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping
  public String register(
      @RequestParam(name = "uname") String username, // Use @RequestParam to capture form data
      @RequestParam(name= "fullname") String fullname,
      @RequestParam(name = "Gender") Integer gender,
      @RequestParam(name = "phonenum") String phonenum,
      @RequestParam(name = "email") String email,
      @RequestParam(name = "password") String password,
      Model model) {
    user s = userService.findUserByUserN(username);
    if (s == null) {
      s = userService.findUserByEmail(email);
      if (s == null) {
        user u = new user(username,
            password,
            fullname,
            gender,
            Integer.parseInt(phonenum),
            email,
            "https://th.bing.com/th/id/R.5097b0247a92d47178df598b82944f15?rik=GOBuYfESpwbvFA&pid=ImgRaw&r=0",
            0,
            roleService.findRoleById(1),
            "user", LocalDate.now());
            u.setRolename(roleService.findRoleById(1).getRole_name());
        userService.addNewUser(u);

      } else {
        model.addAttribute("mEmail", "email already used");
        model.addAttribute("Gender", gender);
        return "signup";
      }

    } else {
      model.addAttribute("mUsername", "username already used");
      model.addAttribute("Gender", gender);
      return "signup";
    }

    return "redirect:/login";
  }

  // Confirm Account
  @RequestMapping(value = "confirm")
  // @RequestParam can de truy cập trang
  public String ConfirmAccount(@RequestParam("token") String token,
      Model model) {
    if (cTokenService.confirmAcount(token).isPresent()) {
      if (cTokenService.confirmAcount(token).get().getUser().getUser_id() == 1) {
        model.addAttribute("message", "Email already activate");
      } else {
        if (cTokenService.confirmAcount(token).get().getExpiredAt().isBefore(LocalDateTime.now())) {
          model.addAttribute("message", " TokenExipired");
          userService.deleteUser(
              userService.findUserById(cTokenService.confirmAcount(token).get().getUser().getUser_id()).getStatus());
        } else {
          model.addAttribute("message", "Confirmed");
          userService.updateUserByUserid(cTokenService.confirmAcount(token).get().getUser().getUser_id());
        }
      }
    } else {
      model.addAttribute("message", "Invalid token");
    }

    return "confirmsignup";
  }

}
