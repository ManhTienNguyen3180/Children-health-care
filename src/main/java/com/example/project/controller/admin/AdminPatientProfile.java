package com.example.project.controller.admin;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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

import com.example.project.entity.patient;
import com.example.project.entity.reservation;
import com.example.project.service.DoctorServiceImpl;
import com.example.project.service.PatientService;
import com.example.project.service.ReservationService;


@Controller
@RequestMapping("admin/patient-profile")
public class AdminPatientProfile {
  @Autowired
  private PatientService patientService;
  @Autowired
  private ReservationService reservationService;
  @Autowired
  private DoctorServiceImpl doctorservice;

  @GetMapping
  public String page(@RequestParam("id") String Patientid, Model model) {
    patient p = patientService.findByPatientId(Integer.parseInt(Patientid)).get();
    try {
      details_Patient d = new details_Patient();
      Optional<patient> patientOptional = patientService.findByPatientId(Integer.parseInt(Patientid));
      if (patientOptional.isPresent()) {
          Optional<details_Patient> detailsPatientOptional = patientService.findByPatientIdDetail(patientOptional.get());
          if (detailsPatientOptional != null && detailsPatientOptional.isPresent()) {
            d = detailsPatientOptional.get();
        } 
      }
      if(d!=null){
      LocalDateTime localDateTime = d.getCreate_at(); 
      int hour = localDateTime.getHour();
      int minute = localDateTime.getMinute();
      model.addAttribute("datetime", localDateTime);
      model.addAttribute("doctorNew", doctorservice.findById(d.getDoctor_id()));
     
      model.addAttribute("date", localDateTime.toLocalDate());
      model.addAttribute("time", String.format("%02d:%02d", hour, minute));
      model.addAttribute("PatientDetail", d);
    }
    } catch (Exception e) {
      // details_Patient d = new details_Patient();
      // d.setBMI("unknow");
      // d.setBlood("unknow");
      // d.setBody_temperature(0);
      // d.setDescription("N/A");

      // d.setEyes_description("unknow");
      // d.setFamily_medical_history("unknow");
      // d.setHeal_description("unknow");
      // d.setHeartbeat(0);
      // d.setHeight("unknow");
      // d.setHemoglobin(0);
      // d.setIOP("unknow");
      // d.setLefteye(0);
      // d.setLefteye_description("unknow");
      // d.setMedical_history("unknow");
      // d.setRighteye(0);
      // d.setRighteye_description("unknow");
      // d.setWeight("unknow");
      // d.setCreate_by("unknow");
      // doctor doc = new doctor();
      // doc.setDoctor_name("Not provided");
      // LocalDateTime localDateTime = LocalDateTime.of(0001, 1, 1, 0, 0, 0);
      // model.addAttribute("datetime", localDateTime);
      // model.addAttribute("doctorNew", doc);
      // int hour = localDateTime.getHour();
      // int minute = localDateTime.getMinute();
      // model.addAttribute("date", localDateTime.toLocalDate());
      // model.addAttribute("time", String.format("%02d:%02d", hour, minute));
      // model.addAttribute("PatientDetail", d);
      // TODO: handle exception
    }

    // Extract hours and minutes

    if (p != null) {
      // SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd
      // HH:mm:ss.SSSSSS");
      // SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      // Date originalDate = null;
      // String formattedDate = null;

      // try {
      // originalDate = originalDateFormat.parse(p.getDob().toString()); // Đọc chuỗi
      // ngày
      // formattedDate = targetDateFormat.format(originalDate); // Định dạng lại thành
      // "yyyy-MM-dd"
      // } catch (ParseException e) {
      // // Xử lý lỗi định dạng
      // }
      LocalDate curDate = LocalDate.now();
      LocalDate dob = p.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      Period age = Period.between(dob, curDate);
      model.addAttribute("age", age.getYears());
      model.addAttribute("patient", p);
      Date date = Date.from(p.getDob().toInstant());
      model.addAttribute("patientInfo", p);
      // Create a SimpleDateFormat for the desired format
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      // Format the Date as a String in "yyyy-MM-dd" format
      String formattedDate = dateFormat.format(date);
      model.addAttribute("dob", formattedDate);
      List<reservation> reservations = reservationService.listReservationByPatientId(Integer.parseInt(Patientid));
      model.addAttribute("reservation", reservations);
    }

    return "admin/patient-profile";
  }

  @PostMapping
  public String modify(@RequestParam("id") String Patientid,
      @RequestParam("email") String email,
      @RequestParam("number") String number,
      @RequestParam("address") String address,
      @RequestParam("status") String status,
      @RequestParam(value = "date", required = false) String date, Model model) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      reservationService.findByPatientDate(Integer.parseInt(Patientid), dateFormat.format(date));
    } catch (Exception e) {
      // TODO: handle exception
    }

    patient p = patientService.findByPatientId(Integer.parseInt(Patientid)).get();
    if (p != null) {
      p.setPatient_email(email);
      p.setPatient_phone(number);
      p.setPatient_address(address);
      p.setStatus(Integer.parseInt(status));
      patientService.updatePatientBypatientId(Integer.parseInt(Patientid), p);
      model.addAttribute("patient", p);
    }
    return page(Patientid, model);
  }
}
