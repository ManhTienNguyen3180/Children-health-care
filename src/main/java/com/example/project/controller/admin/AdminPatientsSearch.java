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
@RequestMapping("admin/patientsSearch")
public class AdminPatientsSearch {
  @Autowired
  private final PatientService patientService;

  public AdminPatientsSearch(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping
  public String page(Model model,
      @RequestParam(name = "s") String r,
      @RequestParam(name = "pageNo", required = false) Integer pageno,
      @RequestParam(name = "order", required = false) String order, HttpSession session) {

    if (pageno == null) {
      pageno = 1;
    }
    model.addAttribute("searchValue", r);
    if (order != null) {

      if (!order.equalsIgnoreCase("gender") && !order.equalsIgnoreCase("status")) {

        // filter gender
        if (order.equalsIgnoreCase("male") ||
            order.equalsIgnoreCase("female")) {
          int i;
          if (order.equalsIgnoreCase("male")) {
            i = 0;
          } else {
            i = 1;
          }

          return findUsersAndFilterGender(r, i, pageno, model, session);
        }

        if (order.equalsIgnoreCase("1") ||
            order.equalsIgnoreCase("0")) {

          return findUsersAndFilterStatus(r, Integer.parseInt(order), pageno, model, session);
        }
      }
    }
    return findPaginatedContainsWithPaging(r, model, pageno, session);
  }

  @PostMapping
  public String Order(Model model, @RequestParam("s") String searchValue,
      @RequestParam(name = "pageNo", required = false) Integer pageno,
      @RequestParam(name = "order", required = false) String order, HttpSession session) {
    model.addAttribute("searchValue", searchValue);
    if (order.equalsIgnoreCase("gender")) {

      model.addAttribute("genderActive", -1);

      return findPaginatedContainsWithPaging(searchValue, model, 1, session);
    } else if (order.equalsIgnoreCase("status")) {

      model.addAttribute("statusActive", -1);
      return findPaginatedContainsWithPaging(searchValue, model, 1, session);
    } else if (order.equalsIgnoreCase("default")) {

      return findPaginatedContainsWithPaging(searchValue, model, 1, session);
    }
    return page(model, searchValue, pageno, order, session);
  }

  public String findPaginatedContainsWithPaging(String searchValue, Model model, Integer pageno, HttpSession session) {
    int pagesize = 3;
    Page<patient> page;
    user u = (user) session.getAttribute("user");
    if (u.getRole_id().getRole_id() == 4) {
      page = patientService.findPaginatedContainsWithPagingByDoctor(u.getFull_name(), searchValue, pageno, pagesize);
    } else {
      page = patientService.findPaginatedContainsWithPaging(searchValue, pageno, pagesize);
    }
    List<patient> listPatient = page.getContent();
    model.addAttribute("listPatients", listPatient);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    return "admin/patientsSearch";
  }

  public String findUsersAndFilterGender(String searchValue, int r, Integer pageno,
      Model model, HttpSession session) {
    int pagesize = 3;
    String s = "Female";
    if (r == 0) {
      s = "male";
    }
    Page<patient> page;
    user u = (user) session.getAttribute("user");
    if (u.getRole_id().getRole_id() == 4) {
      page = patientService.findUsersContainsAndFilterGenderWithPagingByDoctor(u.getFull_name(), searchValue, r, pageno,
          pagesize);
    } else {
      page = patientService.findUsersContainsAndFilterGenderWithPaging(searchValue, r, pageno, pagesize);
    }
    List<patient> listuser = page.getContent();
    model.addAttribute("genderActive", -1);
    model.addAttribute("listPatients", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + s);
    return "admin/patientsSearch";
  }

  public String findUsersAndFilterStatus(String searchValue, int r, Integer pageno,
      Model model, HttpSession session) {
    int pagesize = 3;
    Page<patient> page;
    user u = (user) session.getAttribute("user");
    if (u.getRole_id().getRole_id() == 4) {
      page = patientService.findUsersContainsAndFilterStatusWithPagingByDoctor(u.getFull_name(), searchValue, r, pageno,
          pagesize);
    } else {
      page = patientService.findUsersContainsAndFilterStatusWithPaging(searchValue, r, pageno, pagesize);
    }
    List<patient> listuser = page.getContent();
    model.addAttribute("statusActive", -1);
    model.addAttribute("listPatients", listuser);
    model.addAttribute("currentPage", pageno);
    model.addAttribute("totalPage", page.getTotalPages());
    model.addAttribute("orders", "order=" + r);
    return "admin/patientsSearch";
  }
}
