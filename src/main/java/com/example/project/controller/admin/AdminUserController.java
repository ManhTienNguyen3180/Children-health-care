package com.example.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.role;
import com.example.project.entity.user;
import com.example.project.service.RoleService;
import com.example.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminUserController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private final UserService userService;

  public AdminUserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("admin/users")
  public String page(Model model, @RequestParam(name = "pageNo", required = false) Integer pageno,
      @RequestParam(name = "order", required = false) String order, HttpSession session) {
    if (pageno == null) {
      pageno = 1;
    }

    model.addAttribute("userSession", session.getAttribute("user"));
    if (order != null) {
      if (!order.equalsIgnoreCase("role") ||
          !order.equalsIgnoreCase("gender") ||
          !order.equalsIgnoreCase("status")) {
        // filter role
        if (order.equalsIgnoreCase("ROLE_CUSTOMER") ||
            order.equalsIgnoreCase("ROLE_MANAGER") ||
            order.equalsIgnoreCase("ROLE_ADMIN") ||
            order.equalsIgnoreCase("ROLE_DOCTOR")) {
          role r = roleService.findUserByName(order);

          return findPaginatedWithFilter(r, pageno, model);
        }
        // filter gender
        if (order.equalsIgnoreCase("male") ||
            order.equalsIgnoreCase("female")) {
          int i;
          if (order.equalsIgnoreCase("male")) {
            i = 0;
          } else {
            i = 1;
          }

          return findUsersAndFilterGender(i, pageno, model);
        }

        if (order.equalsIgnoreCase("1") ||
            order.equalsIgnoreCase("0")) {

          return findUsersAndFilterStatus(Integer.parseInt(order), pageno, model);
        }
      }
    }

    return findPaginated(pageno, model);
  }

  @PostMapping("/admin/users")
  public String Order(Model model,
      @RequestParam(name = "order") String order, @RequestParam(name = "pageNo", required = false) Integer pageno,
      HttpSession session) {
    model.addAttribute("userSession", session.getAttribute("user"));
    if (order.equalsIgnoreCase("role")) {

      model.addAttribute("roleActive", -1);

      return findPaginated(1, model);
    } else if (order.equalsIgnoreCase("gender")) {

      model.addAttribute("genderActive", -1);

      return findPaginated(1, model);
    } else if (order.equalsIgnoreCase("status")) {
      model.addAttribute("statusActive", -1);
      return findPaginated(1, model);
    }

    return page(model, pageno, order, session);
  }

  public String findPaginated(Integer pageno,
      Model model) {
    int pagesize = 4;

    Page<user> page = userService.FindPaginated(pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());

    return "admin/users";
  }

  public String findPaginatedWithFilter(role r, Integer pageno,
      Model model) {
    int pagesize = 4;

    Page<user> page = userService.findUsersAndFilterRole(r, pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("roleActive", -1);
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + r.getRole_name());

    return "admin/users";
  }

  public String findUsersAndFilterGender(int r, Integer pageno,
      Model model) {
    int pagesize = 4;
    String s = "Female";
    if (r == 0) {
      s = "male";
    }
    Page<user> page = userService.findUsersAndFilterGender(r, pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("genderActive", -1);
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + s);
    return "admin/users";
  }

  public String findUsersAndFilterStatus(int r, Integer pageno,
      Model model) {
    int pagesize = 4;

    Page<user> page = userService.findUsersAndFilterStatus(r, pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("statusActive", -1);
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + r);
    return "admin/users";
  }

  @GetMapping("/admin/delete")
  public String changeStatus(@RequestParam("id")int id){
    userService.updateStatus(id);
    return "redirect:/admin/users";
  }

}
