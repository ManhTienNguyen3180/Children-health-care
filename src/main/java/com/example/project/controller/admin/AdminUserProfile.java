package com.example.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("admin/user-profile")
public class AdminUserProfile {
  @Autowired
  private final UserService userService;

  public AdminUserProfile(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  private RoleService roleService;

  @GetMapping()
  public String page(@RequestParam("id") String id,
      Model model) {
    user u = userService.findUserById(Integer.parseInt(id));
    List<role> r = roleService.getRole();
    model.addAttribute("user", u);
    model.addAttribute("listrole", r);
    return "admin/user-profile";
  }

  @PostMapping()
  public String changeProfile(@RequestParam("id") String id,
      @RequestParam("status") String status,
      @RequestParam("role") String roleid,
      Model model) {

    userService.updateUserByUserid2(Integer.parseInt(id), status, roleid);
    model.addAttribute("mess", "Update Successful");
    return "redirect:/admin/user-profile?id=" + id;
  }
}