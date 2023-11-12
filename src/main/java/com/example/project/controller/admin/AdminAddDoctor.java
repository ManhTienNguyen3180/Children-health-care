package com.example.project.controller.admin;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.entity.doctor;
import com.example.project.service.DoctorService;
import com.example.project.service.ServiceCategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminAddDoctor {

  @Autowired
  private ServiceCategoryService ServiceCategoryService;
  @Autowired
  private DoctorService DoctorService;

  @GetMapping("/admin/add-doctor")
  public String AdminAddD(Model model) {
    model.addAttribute("cateservice", ServiceCategoryService.fetchServiceCategoryList());
    return "admin/add-doctor";
  }

  public String getImageName(String name) {
    String[] arr = name.split("/");
    return arr[arr.length - 1];
  }

  public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
  }

  public void reFill(RedirectAttributes redirectAttributes, String doctor_name, String position, String phone,
      int service_id, String description, Date dob) {
    redirectAttributes.addFlashAttribute("rename", doctor_name);
    redirectAttributes.addFlashAttribute("reposition", position);
    redirectAttributes.addFlashAttribute("rephone", phone);
    redirectAttributes.addFlashAttribute("redepartment", service_id);
    redirectAttributes.addFlashAttribute("redes", description);
    redirectAttributes.addFlashAttribute("redob", dob);
  }

  @PostMapping("admin/add-doctor/save")
  public String saveDoctor(
      @RequestParam("docname") String doctor_name,
      @RequestParam("docpos") String position,
      @RequestParam("docdob") Date dob,
      @RequestParam("docphone") String phone,
      @RequestParam("service_id") int service_id,
      @RequestParam("gender") String gender,
      @RequestParam("docimg") MultipartFile file,
      @RequestParam("docdes") String description,
      Model model, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
    doctor doctor = new doctor();

    LocalDate now = LocalDate.now();
    LocalDate dob1 = convertToLocalDateViaSqlDate(dob);
    int age = Period.between(dob1, now).getYears();

    doctor d = DoctorService.findDoctor(position, phone);
    doctor d2 = DoctorService.findDoctorPosition("Bác sĩ");
    
    String regexPhoneNumber = "^0[0-9]{9}$";
    Pattern pattern = Pattern.compile(regexPhoneNumber);
    Matcher matcher = pattern.matcher(phone);
    int phonenum = Integer.parseInt(phone);

    if (d == null) {
      if (age >= 18) {
        String uploadDir = "./src/main/resources/static/images";

        if (file.isEmpty()) {
          doctor.setImage("/images/defaulavatar.png");
        } else {
          try {
            java.nio.file.Path copyLocation = Paths
                .get(uploadDir + java.io.File.separator + file.getOriginalFilename());
            java.nio.file.Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            doctor.setImage("/images" + "/" + file.getOriginalFilename());
          } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Fail to upload " + file.getOriginalFilename() + "!");
          }
        }

        doctor.setDoctor_name(doctor_name);

        doctor.setPosition(position);

        if (gender.matches("Male")) {
          doctor.setGender(1);
        } else {
          doctor.setGender(0);
        }

        if (matcher.find()) {

          doctor.setPhone(phonenum);
        } else {
          redirectAttributes.addFlashAttribute("phonemessage", "Phone must start with 0 and have 10 number");
          reFill(redirectAttributes, doctor_name, position, phone, service_id, description, dob);
          return "redirect:/admin/add-doctor";
        }

        doctor.setDescription(description);

        doctor.setDob(dob);

        doctor.setStatus(1);
        doctor.setCreate_at(new java.sql.Date(System.currentTimeMillis()));
        doctor.setCreate_by("admin");
        doctor.setDoctorserviceId(service_id);
        DoctorService.save(doctor);

        redirectAttributes.addFlashAttribute("successmessage", "Doctor added successfully!");
        return "redirect:/admin/add-doctor";
      } else {
        redirectAttributes.addFlashAttribute("dobmessage", "Doctor must have at least 18 years old");
        reFill(redirectAttributes, doctor_name, position, phone, service_id, description, dob);
      }
    } else {
      if (d2.getPosition().equalsIgnoreCase(position)) {
        if (age >= 18) {
          String uploadDir = "./src/main/resources/static/images";

          if (file.isEmpty()) {
            doctor.setImage("/images/defaulavatar.png");
          } else {
            try {
              java.nio.file.Path copyLocation = Paths
                  .get(uploadDir + java.io.File.separator + file.getOriginalFilename());
              java.nio.file.Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
              doctor.setImage("/images" + "/" + file.getOriginalFilename());
            } catch (Exception e) {
              e.printStackTrace();
              session.setAttribute("message", "Fail to upload " + file.getOriginalFilename() + "!");
            }
          }

          doctor.setDoctor_name(doctor_name);

          doctor.setPosition(position);

          if (gender.matches("Male")) {
            doctor.setGender(1);
          } else {
            doctor.setGender(0);
          }

          if (matcher.find()) {

            doctor.setPhone(phonenum);
          } else {
            redirectAttributes.addFlashAttribute("phonemessage", "Phone must start with 0 and have 10 number");
            reFill(redirectAttributes, doctor_name, position, phone, service_id, description, dob);
            return "redirect:/admin/add-doctor";
          }

          doctor.setDescription(description);

          doctor.setDob(dob);

          doctor.setStatus(1);
          doctor.setCreate_at(new java.sql.Date(System.currentTimeMillis()));
          doctor.setCreate_by("admin");
          doctor.setDoctorserviceId(service_id);
          DoctorService.save(doctor);

          redirectAttributes.addFlashAttribute("successmessage", "Doctor added successfully!");
        } else {
          redirectAttributes.addFlashAttribute("dobmessage", "Doctor must have at least 18 years old");
          reFill(redirectAttributes, doctor_name, position, phone, service_id, description, dob);
        }
      } else {
        reFill(redirectAttributes, doctor_name, position, phone, service_id, description, dob);
        if (d.getPosition().equalsIgnoreCase(position)) {
          redirectAttributes.addFlashAttribute("posmessage", "Position is exist!");
        }
        if (d.getPhone() == phonenum) {
          redirectAttributes.addFlashAttribute("phonemessage", "Phone is exist!");
        }
      }
    }

    return "redirect:/admin/add-doctor";
  }
}
