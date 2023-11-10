package com.example.project.controller.admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.details_Patient;
import com.example.project.entity.doctor;

import com.example.project.entity.patient;
import com.example.project.entity.user;
import com.example.project.service.ContactService;
import com.example.project.service.DoctorService;
import com.example.project.service.PatientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/add-patientDetails")
public class AdminAddPatientDetails {
  @Autowired
  private PatientService PatientService;
  @Autowired
  private ContactService contactService;
  @Autowired
  private DoctorService dService;

  @GetMapping
  public String pageAfteradd(Model model, @RequestParam("id") String id) {

    Optional<patient> optionalPatient = PatientService.findByPatientId(Integer.parseInt(id));

    if (optionalPatient.isPresent()) {
      patient p = optionalPatient.get();
      Optional<details_Patient> optionalPatientDetail = PatientService
          .findByPatientIdDetail(p);
      model.addAttribute("patientInfo", p);
      List<doctor> listd = dService.fetchDoctorList();
      model.addAttribute("listd", listd);
      if (optionalPatientDetail.isPresent()) {
        details_Patient pd = optionalPatientDetail.get();
        model.addAttribute("detailpatient", pd);
        LocalDateTime createByDate = pd.getCreate_at();
        if (createByDate != null) {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
          DateTimeFormatter formatterdate = DateTimeFormatter.ofPattern("YYYY-MM-dd");
          model.addAttribute("datetime", pd.getCreate_at());
          String formattedTime = formatter.format(createByDate);
          model.addAttribute("date", formatterdate.format(createByDate));
          model.addAttribute("time", formattedTime);
        } else {
          model.addAttribute("time", "N/A"); // Handle the case when create_by is null
        }

      } else {
        LocalDateTime localDateTime = LocalDateTime.now();
        model.addAttribute("datetime", localDateTime);
        // Extract hours and minutes
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        model.addAttribute("date", localDateTime.toLocalDate());
        model.addAttribute("time", String.format("%02d:%02d", hour, minute));
      }
    } else {
      model.addAttribute("mess", "Patient not found");
      return "admin/add-patient";
    }
    return "admin/add-patientDetails";
  }


  @PostMapping
  public String addDetail(Model model,
      HttpSession session,
      @RequestParam("id") String id,
      @RequestParam("doctor") String doctor,
      @RequestParam("datetime") LocalDateTime datetime,
      @RequestParam("heartbeat") int heartbeat,
      @RequestParam("bodytem") int bodytem,
      @RequestParam("weight") String weight,
       @RequestParam("height") String height,
      @RequestParam("mhistory") String mhistory,
      @RequestParam("blood") String blood,
     
      @RequestParam("description") String description,
      @RequestParam(value = "reservation_id", required = false) Integer reservation_id ) {
        
    try {
      if(!weight.isEmpty()||!height.isEmpty()){
        try {
          Integer.parseInt(weight);
        } catch (Exception e) {
                model.addAttribute("mess", "Please add valid weight");
            return pageAfteradd(model, id);
        }
        try {
          Integer.parseInt(height);
        } catch (Exception e) {
                model.addAttribute("mess", "Please add valid height");
              return pageAfteradd(model, id);
        }
            
      }
      
      user u = (user) session.getAttribute("user");
      details_Patient pd = new details_Patient();
      pd.setPatient(PatientService.findByPatientId(Integer.parseInt(id)).get());
      pd.setMedical_history(mhistory);
      pd.setCreate_at(datetime);
      pd.setCreate_by(u.getFull_name());
      pd.setHeartbeat(heartbeat);
      pd.setBody_temperature(bodytem);
      pd.setBlood(blood);
      pd.setHeight(height);
      pd.setWeight(weight);
      String formattedBMI = String.format("%.2f", calculateBMI(weight, height));
      pd.setBMI(formattedBMI);
      pd.setDescription(description);
      pd.setHeal_description(BMI(calculateBMI(weight, height)));
      pd.setDoctor_id(Integer.parseInt(doctor));
      pd.setReservation_id(reservation_id);
      if (PatientService.findByPatientIdHadReserId(PatientService.findByPatientId(Integer.parseInt(id)).get(), reservation_id)
          .isPresent()) {
        model.addAttribute("mess", "You can add patient detail one time per reservation");
        return pageAfteradd(model, id);
      }
      PatientService.addPatient(pd, reservation_id);
      model.addAttribute("mess", "Add success");
      return "redirect:/admin/patient-profile?id=" + id;
    } catch (Exception e) {
      // TODO: handle exception
      model.addAttribute("mess", "Add fail");
    }
    
    return pageAfteradd(model, id);
  }

  private double calculateBMI(String weightKg, String heightCm) {
    try {
      // Convert height from centimeters to meters
      double heightM = Double.parseDouble(heightCm) / 100.0;

      // Calculate BMI using the formula: BMI = weight (kg) / (height (m) * height
      // (m))
      double bmi = Double.parseDouble(weightKg) / (heightM * heightM);
      // Determine the BMI category

      return bmi;
    } catch (Exception e) {
      // TODO: handle exception
    }
    return 0;
  }

  private String BMI(double bmi) {
    String category;
    if (bmi < 18.5) {
      category = "Underweight";
    } else if (bmi >= 18.5 && bmi < 24.9) {
      category = "Normal Weight";
    } else if (bmi >= 25 && bmi < 29.9) {
      category = "Overweight";
    } else {
      category = "Obese";
    }
    if (bmi == 0) {
      category = "Unknow";
    }
    return category;
  }
}
