package com.example.project.controller.admin;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.entity.contact;
import com.example.project.entity.patient;
import com.example.project.entity.user;
import com.example.project.service.ContactService;
import com.example.project.service.PatientService;

import jakarta.mail.internet.ParseException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/add-patient")
public class AdminAddPatient {
  @Autowired
  private PatientService PatientService;
  @Autowired
  private ContactService contactService;

  @GetMapping
  public String page(Model model) {
   
    return "admin/add-patient";
  }

  @PostMapping
  public String add(Model model,
      @RequestParam("image") MultipartFile image,
      @RequestParam("full_name") String fullName,
      @RequestParam("province") String province,
      @RequestParam("district") String district,
      @RequestParam("ward") String ward,
      @RequestParam("dob") String dob,
      @RequestParam("gender") String gender,
      @RequestParam("Email") String Email,
      @RequestParam("phonenum") String phonenum,
      HttpSession session) {

    String imageAddress = "";
    if (image.isEmpty()) {
      imageAddress = "/images/blog/default-blog.png";
    } else {
      try {
        // We can save image in 'images' directory in roo

        String uploadDir = "./src/main/resources/static/images/patient";
        java.nio.file.Path copyLocation = Paths
            .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
        java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        imageAddress = "/images/patient" + "/" + image.getOriginalFilename();
      } catch (Exception e) {
        imageAddress = "/images/patient" + "/" + image.getOriginalFilename();
      }

    }
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date datem = null;

      try {
        datem = dateFormat.parse(dob);
      } catch (java.text.ParseException e) {
        e.printStackTrace();
        // Handle the exception here, e.g., log an error or take appropriate action
      }

      LocalDate localDate = LocalDate.now();
      // Convert LocalDate to Date
      Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
      user u = (user) session.getAttribute("user");
      patient p = new patient();
      p.setDob(date);
      if (datem != null) {
        p.setCreate_at(datem);
      } else {
        model.addAttribute("mess", "Cant pasrse dob");
        return page(model);
      }

      p.setCreate_by(u.getFull_name());
      p.setGender(Integer.parseInt(gender));
      p.setImage(imageAddress);
      p.setPatient_name(fullName);
      p.setStatus(0);
      p.setUser_id(u.getUser_id());
      p.setPatient_email(Email);
      p.setPatient_phone(phonenum);
      p.setPatient_address(province + ", " + district + ", " + ward);
      PatientService.addPatient(p);
      patient pwithId = PatientService.findByPatientEmail(p.getPatient_email());
      contact c = new contact();
      if (contactService.findByPatientId(pwithId) != null) {
        model.addAttribute("mess", "Patient already has address");
        return page(model);
      } else {
        c.setProvince(province);
        c.setDistrict(district);
        c.setWard(ward);
        c.setPatient(pwithId);
        contactService.addContact(c);
      }

      model.addAttribute("mess", "Add success");
      return "redirect:/admin/add-patientDetails?id="+pwithId.getPatient_id(); // Fixed the URL
    } catch (Exception e) {
      // TODO: handle exception
      model.addAttribute("mess", "Add fail");
    }
    return page(model);
  }

  @RequestMapping(path = "/add-patientDetails", method = RequestMethod.GET)
  public String pageAfteradd(Model model) {
      model.addAttribute("patients", PatientService.findByPatientId(1).get());
      return "admin/add-patientDetails";
  }
  

}
