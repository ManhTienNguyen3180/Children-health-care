package com.example.project.controller.admin;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

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
import com.example.project.entity.doctorservice;
import com.example.project.service.DoctorService;
import com.example.project.service.ServiceService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminAddDoctor {

  @Autowired
  private ServiceService ServiceService;
  @Autowired
  private DoctorService DoctorService;

  @GetMapping("/admin/add-doctor")
  public String AdminAddD(Model model) {
    model.addAttribute("servicelist", ServiceService.fechServicesList());
    return "admin/add-doctor";
  }

  public String getImageName(String name) {
    String[] arr = name.split("/");
    return arr[arr.length - 1];
  }

  @PostMapping("admin/add-doctor/save")
  public String saveDoctor(
      @RequestParam("docname") String doctor_name,
      @RequestParam("docpos") String position,
      @RequestParam("docdob") Date dob,
      @RequestParam("docphone") int phone,
      @RequestParam("service_id") Integer[] service_id,
      @RequestParam("gender") String gender,
      @RequestParam("docimg") MultipartFile file,
      @RequestParam("docdes") String description,
      Model model, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
    doctor doctor = new doctor();
    

    String uploadDir = "./src/main/resources/static/images";

    if(file.isEmpty()) {
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
    
    doctor.setPhone(phone);
    
    doctor.setDescription(description);
    Date now = new java.sql.Date(System.currentTimeMillis());
    if(dob.before(now)) {
      doctor.setDob(dob);
    } else {
      redirectAttributes.addFlashAttribute("message", "DoB must before now");
      return "redirect:/admin/add-doctor";
    }
    doctor.setStatus(1);
    doctor.setCreate_at(new java.sql.Date(System.currentTimeMillis()));
    doctor.setCreate_by("admin");
    DoctorService.save(doctor);

    //Tìm theo last id

    doctor d = DoctorService.findLatestDoctor();
    int docid = d.getDoctor_id();

    for(Integer serviceId : service_id) {
      doctorservice doctorservice = new doctorservice();
        doctorservice.setDoctorID(docid);
        doctorservice.setServiceID(serviceId);
        doctorservice.setStatus(1);
        DoctorService.savedocservice(doctorservice);
      }
        
    redirectAttributes.addFlashAttribute("successmessage", "Doctor added successfully!");

    return "redirect:/admin/doctors";
  }
}
