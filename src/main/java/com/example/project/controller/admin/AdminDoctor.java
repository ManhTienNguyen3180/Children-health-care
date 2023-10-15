package com.example.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.project.entity.doctor;
import com.example.project.service.DoctorService;
import com.example.project.service.ServiceService;

@Controller
public class AdminDoctor {

  @Autowired
  private DoctorService DoctorService;
  
  @Autowired
  private ServiceService ServiceService;

  @GetMapping("admin/doctors")
  public String viewDoctorList(Model model) {
    String keyword = null;
    return findPaginatedDoctor(1, "status", "desc",keyword, model);
  }

  @GetMapping("/admin/doctors/page/{pageNo}")
  public String findPaginatedDoctor(@PathVariable int pageNo,
      @RequestParam(value = "sortField") String sortField,
      @RequestParam("sortDir") String sortDir,
      @Param("keyword") String keyword,
      Model model) {

    int pageSize = 3;

    Page<doctor> page = DoctorService.findPaginatedDoctor(pageNo, pageSize, sortField, sortDir, keyword);
    List<doctor> list = page.getContent();

    model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("doctor", list);

    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    
    model.addAttribute("keyword", keyword);
    model.addAttribute("service", ServiceService.fechServicesList());
    model.addAttribute("docservice", DoctorService.fetchDoctorservicesList());

    return "admin/doctors";
  }

}
