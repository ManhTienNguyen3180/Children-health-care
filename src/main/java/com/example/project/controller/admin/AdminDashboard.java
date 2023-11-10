package com.example.project.controller.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.Admin.BlogController.Repository.BlogRepository;
import com.example.project.Admin.BlogController.Service.BlogsService;
import com.example.project.Admin.ServiceCategoryController.Service.ServiceCategoryService;
import com.example.project.Repository.DoctorRepo;
import com.example.project.Repository.ReservationRepo;
import com.example.project.entity.doctor;
import com.example.project.entity.reservation;
import com.example.project.service.DoctorService;
import com.example.project.service.PatientService;
import com.example.project.service.ReservationService;

@Controller
@RequestMapping("admin/dashboard")
public class AdminDashboard {
    @Autowired
    private DoctorService docSer;

    @Autowired
    private ServiceCategoryService serSer;

    @Autowired
    private PatientService parSer;

    @Autowired
    private ReservationService resSer;

    @Autowired
    private ServiceCategoryService serCatSer;

    @GetMapping("")
    public String renderDashboard(Model model, @RequestParam(name = "year", required = false) Integer select) {

        LocalDate current_date = LocalDate.now();
        int year = current_date.getYear();

        model.addAttribute("minYear", year - 10);
        model.addAttribute("maxYear", year + 10);
        List<Integer> monthlyReservationData;
        if (select == null) {
            monthlyReservationData = resSer.countReservationsByMonth(year);
            model.addAttribute("selectedYear", year);
        } else {
            monthlyReservationData = resSer.countReservationsByMonth(select);
            model.addAttribute("selectedYear", select);
        }
        Integer total= 0;
        for (Integer integer : monthlyReservationData) {
            total+=integer;
        }
        
        // Pass the data to the Thymeleaf template
        model.addAttribute("monthlyReservationData", monthlyReservationData);
        model.addAttribute("totalperyear", total);

        
        model.addAttribute("depNum", serCatSer.findAll().size());



        model.addAttribute("numDoctors", docSer.findPaginated(1, 1).getTotalElements());
        model.addAttribute("numPatient", parSer.getPatient().size());
        model.addAttribute("numAppointment", resSer.findAll().size());
        model.addAttribute("listDepartment", serSer.findAll());
        return "admin/index";
    }

}
