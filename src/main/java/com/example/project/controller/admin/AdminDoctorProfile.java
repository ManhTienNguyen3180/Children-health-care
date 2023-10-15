package com.example.project.controller.admin;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.entity.doctor;
import com.example.project.entity.doctorservice;
import com.example.project.entity.service;
import com.example.project.entity.slot;
import com.example.project.service.DoctorService;
import com.example.project.service.ScheduleService;
import com.example.project.service.ServiceService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;


@Controller
public class AdminDoctorProfile {

  @Autowired
  private DoctorService DoctorService;
  @Autowired
  private ServiceService ServiceService;
  @Autowired
  private ScheduleService ScheduleService;

  @GetMapping("/admin/dr-profile")
  public String page() {
    return "admin/dr-profile";
  }

  //Delete doctor
  @RequestMapping("/admin/doctors/delete/{doctor_id}")
  public String deleteDoctor(@PathVariable(name = "doctor_id") int id, RedirectAttributes redirectAttributes) {
    
    List<doctorservice> ds = DoctorService.finDoctorservicesByDoctorID(id);
    for (doctorservice doctorservice : ds) {
      DoctorService.deleteDoctorService(doctorservice.getId()); 
    }
      
    slot s = ScheduleService.findSlotByDoctorID(id);
    if(s != null) {
      int slotid = s.getId();
      DoctorService.deleteSlot(slotid);
    }

    redirectAttributes.addFlashAttribute("successmessage", "Doctor deleted successfully!");
    DoctorService.deleteDoctor(id);
    
    return "redirect:/admin/doctors";
  }

  //Profile
  @RequestMapping("/admin/doctors/profile/{doctor_id}")
  public String profileDoctor(@PathVariable(name = "doctor_id") int id, Model model) {
    doctor doctor = DoctorService.findDoctorById(id).get();  
    List<slot> slot = ScheduleService.getSlotsByDoctorId(id);
    List<service> service = ServiceService.findServiceByDocID(id);
    model.addAttribute("slot", slot);
    model.addAttribute("doctor", doctor);
    model.addAttribute("service", service);
    return "admin/dr-profile";
  }


  @GetMapping("/admin/edit-doctor")
  public String editDoctor(Model model) {
    return "admin/edit-doctor";
  }

  //Edit
  @RequestMapping("/admin/doctors/edit/{doctor_id}")
    public String editDoctor(@PathVariable(name = "doctor_id") int id, Model model) {
        doctor doctor = DoctorService.findDoctorById(id).get();
        List<service> services = ServiceService.findServiceByDocID(id);
        model.addAttribute("service", ServiceService.fechServicesList());
        model.addAttribute("current", services);
        model.addAttribute("doctor", doctor);
        return "admin/edit-doctor";
    }

  @RequestMapping(value = "/admin/edit-doctor/save", method = RequestMethod.POST)
  public String saveDoctor(
      @PathParam("doctor_id") int doctor_id,
      @PathParam("docname") String doctor_name,
      @PathParam("docpos") String position,
      @PathParam("docdob") Date dob,
      @PathParam("docphone") int phone,
      @PathParam("service_id") Integer[] service_id,
      @PathParam("gender") String gender,
      @RequestParam("docimg") MultipartFile file,
      @RequestParam("docstatus") String docstatus,
      @PathParam("docdes") String description,
      Model model, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
    Optional<doctor> doc = DoctorService.findDoctorById(doctor_id);
    doctor doctor = doc.get();
    List<doctorservice> doctorservice = DoctorService.finDoctorservicesByDoctorID(doctor.getDoctor_id());


    String uploadDir = "./src/main/resources/static/images";
    if(file.isEmpty()) {
      doctor.setImage(doctor.getImage());
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
    if (gender.equalsIgnoreCase("Male")) {
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

    if (docstatus.equalsIgnoreCase("Active")) {
      doctor.setStatus(1);
    } else {
      doctor.setStatus(0);
    }

    doctor.setCreate_at(new java.sql.Date(System.currentTimeMillis()));
    doctor.setCreate_by("admin");
    DoctorService.save(doctor);

    int docid = doctor.getDoctor_id();
    
    if(service_id == null) {
      redirectAttributes.addFlashAttribute("successmessage", "Doctor changed successfully!");
      return "redirect:/admin/doctors";
    } else {
      List<Integer> list = new ArrayList<>(Arrays.asList(service_id));

    if(doctorservice.size() < list.size()) {
      int n = list.size() - doctorservice.size();
      for(int i=0; i< n; i++) {
        doctorservice docservice = new doctorservice();
        docservice.setDoctorID(docid);
        docservice.setServiceID(0);
        docservice.setStatus(1);
        doctorservice.add(docservice);
      }
    }
    if(doctorservice.size() > list.size()) {
      int m = doctorservice.size() - list.size();
      List<doctorservice> ds = DoctorService.finDoctorservicesByDoctorID(doctor_id);
      for (doctorservice dss : ds) {
        DoctorService.deleteDoctorService(dss.getId()); 
      }
      for(int i=0; i< m; i++) {
        doctorservice docservice = new doctorservice();
        docservice.setDoctorID(docid);
        docservice.setServiceID(0);
        docservice.setStatus(1);
        doctorservice.add(docservice);
      }
    }
    for (doctorservice ds : doctorservice) {
      for (Integer id : list) {
        if(ds.getServiceID() == id) {
          list.remove(id);
          break;
        } else {
          ds.setDoctorID(docid);
          ds.setServiceID(id);
          ds.setStatus(1);
          DoctorService.savedocservice(ds);
          list.remove(id);
          break;
        }      
      }
    }
    }
    redirectAttributes.addFlashAttribute("successmessage", "Doctor changed successfully!");

    return "redirect:/admin/doctors";
  }
}
