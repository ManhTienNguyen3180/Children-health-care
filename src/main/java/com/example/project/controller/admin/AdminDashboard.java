package com.example.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.Admin.BlogController.Repository.BlogRepository;
import com.example.project.Admin.BlogController.Service.BlogsService;
import com.example.project.Repository.DoctorRepo;
import com.example.project.entity.doctor;
import com.example.project.service.DoctorService;

@Controller
@RequestMapping("admin/dashboard")
public class AdminDashboard {
    @Autowired
    private DoctorService docSer;

    @GetMapping("")
    public String renderDashboard(Model model) {

        model.addAttribute("numDoctors", docSer.findPaginated(1,1).getTotalElements());

        return "admin/index";
    }
}
