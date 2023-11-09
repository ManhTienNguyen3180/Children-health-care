package com.example.project.controller.admin;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.entity.contact;
import com.example.project.entity.patient;
import com.example.project.entity.user;
import com.example.project.service.ContactService;
import com.example.project.service.PatientService;

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
    String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    String phonePattern = "^0[1-9]\\d{7,8}$";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date datem = null;
    Date currentDate = Date.from(Instant.now());
    try {
      datem = dateFormat.parse(dob);

    } catch (java.text.ParseException e) {
      e.printStackTrace();
      // Handle the exception here, e.g., log an error or take appropriate action
    }
    if (!Email.matches(emailPattern)
        || !phonenum.matches(phonePattern) || datem.after(currentDate)) {

      if (!Email.matches(emailPattern)) {
        model.addAttribute("Emess", "Please enter a gmail email. Exp:abc@gmail.com");
      }
      if (!phonenum.matches(phonePattern)) {
        model.addAttribute("Pmess",
            "Please enter a valid phone number starting with '0' and followed by 8 or 9 digits.");
      }
      if (datem.after(currentDate)) {
        model.addAttribute("Dobmess",
            "The date of birth cannot exceed the present");
      }
      return page(model);
    }
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
    // try {

      LocalDate localDate = LocalDate.now();
      // Convert LocalDate to Date
      Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
      user u = (user) session.getAttribute("user");
      patient p = new patient();
      p.setDob(date);
      if (datem != null) {
        p.setCreate_at(datem);
      }

      if (PatientService.findByPatientEmail(Email) != null) {
        model.addAttribute("mess",
            "Email already used");
        return page(model);
      }
      p.setCreate_by(u.getFull_name());
      p.setGender(Integer.parseInt(gender));
      p.setImage(imageAddress);
      p.setPatient_name(fullName);
      p.setStatus(0);
      p.setPatient_email(Email);
      p.setPatient_phone(phonenum);
      p.setPatient_address(province + ", " + district + ", " + ward);
      PatientService.addPatient(p);
      patient pwithId = PatientService.findByPatientEmail(Email);
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
      return "redirect:/admin/add-patientDetails?id=" + pwithId.getPatient_id(); // Fixed the URL
    // } catch (Exception e) {
    //   // TODO: handle exception
    //   model.addAttribute("mess", "Add fail");
    // }
    // return page(model);
  }

}
