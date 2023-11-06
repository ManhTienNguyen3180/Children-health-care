package com.example.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("admin/usersSearch")
public class AdminUsersSearch {

  @Autowired
  private RoleService roleService;
  @Autowired
  private final UserService userService;

  public AdminUsersSearch(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String page(Model model,
      @RequestParam(name = "s", required = false) String r,
      @RequestParam(name = "pageNo", required = false) Integer pageno,
      @RequestParam(name = "order", required = false) String order,
      HttpSession session) {
    model.addAttribute("userSession", session.getAttribute("user"));

    // To define href in page
    model.addAttribute("searchName", r);
    if (pageno == null) {
      pageno = 1;
    }
    if (order != null) {

      if (!order.equalsIgnoreCase("role") ||
          !order.equalsIgnoreCase("gender") ||
          !order.equalsIgnoreCase("status")) {
        // filter role
        if (order.equalsIgnoreCase("ROLE_CUSTOMER") ||
            order.equalsIgnoreCase("ROLE_MANAGER") ||
            order.equalsIgnoreCase("ROLE_ADMIN") ||
            order.equalsIgnoreCase("ROLE_DOCTOR")) {
          role rs = roleService.findUserByName(order);

          return findUsersContainAndFilterRoleWithPaging(r, rs, pageno, model);
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

          return findUsersContainsAndFilterGenderWithPaging(r, i, pageno, model);
        }

        if (order.equalsIgnoreCase("1") ||
            order.equalsIgnoreCase("0")) {

          return findUsersContainsAndFilterStatusWithPaging(r, Integer.parseInt(order), pageno, model);
        }
      }
    }

    return findPaginated(r, model, pageno);
  }

  @PostMapping
  public String Order(Model model, @RequestParam("s") String searchValue,
      @RequestParam(name = "pageNo", required = false) Integer pageno,
      @RequestParam(name = "order", required = false) String order,
      HttpSession session) {
    model.addAttribute("userSession", session.getAttribute("user"));
    // To define href in page
    model.addAttribute("searchName", searchValue);
    if (order.equalsIgnoreCase("role")) {

      model.addAttribute("roleActive", -1);
      return findPaginated(searchValue, model, 1);
    } else if (order.equalsIgnoreCase("gender")) {

      model.addAttribute("genderActive", -1);

      return findPaginated(searchValue, model, 1);
    } else if (order.equalsIgnoreCase("status")) {

      model.addAttribute("statusActive", -1);
      return findPaginated(searchValue, model, 1);
    }

    return page(model, searchValue, pageno, order, session);
  }

  public String findPaginated(String s, Model model, Integer pageno) {
    int pagesize = 4;

    Page<user> page = userService.findUsersContainWithPaging(s, pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());

    return "admin/usersSearch";
  }

  public String findUsersContainAndFilterRoleWithPaging(String s, role r, Integer pageno,
      Model model) {
    int pagesize = 4;
        
    Page<user> page = userService.findUsersContainAndFilterRoleWithPaging(s, r, pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("roleActive", -1);
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + r.getRole_name());

    return "admin/usersSearch";
  }

  public String findUsersContainsAndFilterGenderWithPaging(String name, int r, Integer pageno,
      Model model) {
    int pagesize = 4;
    String s = "Female";
    if (r == 0) {
      s = "male";
    }
    Page<user> page = userService.findUsersContainsAndFilterGenderWithPaging(name, r, pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("genderActive", -1);
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + s);
    return "admin/usersSearch";
  }

  public String findUsersContainsAndFilterStatusWithPaging(String name, int r, Integer pageno,
      Model model) {
    int pagesize = 4;

    Page<user> page = userService.findUsersContainsAndFilterStatusWithPaging(name, r, pageno, pagesize);
    List<user> listuser = page.getContent();
    model.addAttribute("statusActive", -1);
    model.addAttribute("listuser", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + r);
    return "admin/usersSearch";
  }
}
