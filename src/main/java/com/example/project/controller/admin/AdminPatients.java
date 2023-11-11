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

import com.example.project.entity.patient;
import com.example.project.entity.user;
import com.example.project.service.PatientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/patients")
public class AdminPatients {
  @Autowired
  private PatientService patientService;

  @GetMapping
  public String page(Model model, @RequestParam(name = "pageNo", required = false) Integer pageno,
      @RequestParam(name = "order", required = false) String order, HttpSession session) {
    if (pageno == null) {
      pageno = 1;
    }

    if (order != null) {
      if (!order.equalsIgnoreCase("gender") ||
          !order.equalsIgnoreCase("status")) {

        // filter gender
        if (order.equalsIgnoreCase("male") ||
            order.equalsIgnoreCase("female")) {
          int i;
          if (order.equalsIgnoreCase("male")) {
            i = 0;
          } else {
            i = 1;
          }

          return findUsersAndFilterGender(i, pageno, model, session);
        }

        if (order.equalsIgnoreCase("1") ||
            order.equalsIgnoreCase("0")) {

          return findUsersAndFilterStatus(Integer.parseInt(order), pageno, model, session);
        }
      }
    }
    return findPaginated(model, pageno, session);
  }

  @PostMapping
  public String Order(Model model,
      @RequestParam(name = "order") String order, @RequestParam(name = "pageNo", required = false) Integer pageno,
      HttpSession session) {

    if (order.equalsIgnoreCase("gender")) {

      model.addAttribute("genderActive", -1);

      return findPaginated(model, 1, session);
    } else if (order.equalsIgnoreCase("status")) {
      model.addAttribute("statusActive", -1);
      return findPaginated(model, 1, session);
    }
    return page(model, pageno, order,session);
  }

  public String findPaginated(Model model, Integer pageno, HttpSession session) {
    int pagesize = 3;

    Page<patient> page;
    user u = (user) session.getAttribute("user");
    if (u.getRole_id().getRole_id() == 4) {
      page = patientService.FindPaginatedByDoctor(u.getFull_name(), pageno, pagesize);
    } else {
      page = patientService.FindPaginated(pageno, pagesize);
    }
    List<patient> listPatient = page.getContent();
    model.addAttribute("listPatients", listPatient);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    return "admin/patients";
  }

  public String findUsersAndFilterGender(int r, Integer pageno,
      Model model, HttpSession session) {
    int pagesize = 3;
    String s = "Female";
    if (r == 0) {
      s = "male";
    }
    Page<patient> page;
    user u = (user) session.getAttribute("user");
    if (u.getRole_id().getRole_id() == 4) {
      page = patientService.findUsersAndFilterGenderByDoctor(u.getFull_name(), r, pageno, pagesize);
    } else {
      page = patientService.findUsersAndFilterGender(r, pageno, pagesize);
    }
    List<patient> listpPatients = page.getContent();

    model.addAttribute("genderActive", -1);
    model.addAttribute("listPatients", listpPatients);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + s);
    return "admin/patients";
  }

  public String findUsersAndFilterStatus(int r, Integer pageno,
      Model model, HttpSession session) {
    int pagesize = 3;
    Page<patient> page;
    user u = (user) session.getAttribute("user");
    if (u.getRole_id().getRole_id() == 4) {
      page = patientService.findUsersAndFilterStatusbyDoctor(u.getFull_name(), r, pageno, pagesize);
    } else {
      page = patientService.findUsersAndFilterStatus(r, pageno, pagesize);

    }
    List<patient> listpPatients = page.getContent();

    model.addAttribute("statusActive", -1);
    model.addAttribute("listPatients", listpPatients);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + r);
    return "admin/patients";
  }

}
