package com.example.project.controller.admin;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.patient;
import com.example.project.entity.reservation;
import com.example.project.service.PatientService;
import com.example.project.service.ReservationService;

@Controller
@RequestMapping("admin/patient-profile")
public class AdminPatientProfile {
  @Autowired
  private PatientService patientService;
  @Autowired
  private ReservationService reservationService;

  @GetMapping
  public String page(@RequestParam("id") String Patientid, Model model) {
    patient p = patientService.findByPatientId(Integer.parseInt(Patientid)).get();
    if (p != null) {
      LocalDate curDate = LocalDate.now();
      LocalDate dob = p.getDob().toLocalDate();
      Period age = Period.between(dob, curDate);
      model.addAttribute("age", age.getYears());
      model.addAttribute("patient", p);
      List<reservation> reservations = reservationService.listReservationByPatientId(Integer.parseInt(Patientid));
      model.addAttribute("reservation", reservations);
    }

    return "admin/patient-profile";
  }

  @PostMapping
  public String modify(@RequestParam("id") String Patientid,
      Model model) {
    patient p = patientService.findByPatientId(Integer.parseInt(Patientid)).get();
    if (p != null) {
      model.addAttribute("patient", p);
    }
    return "admin/patient-profile";
  }
}
