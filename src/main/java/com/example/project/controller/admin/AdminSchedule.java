package com.example.project.controller.admin;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.entity.slot;
import com.example.project.service.DoctorService;
import com.example.project.service.ScheduleService;

@Controller
public class AdminSchedule {

    @Autowired
    private DoctorService DoctorService;
    @Autowired
    private ScheduleService ScheduleService;

    @GetMapping("/admin/schedule")
    public String adminSchedule(Model model) {
        model.addAttribute("doctorlist", DoctorService.fetchDoctorList());
        return "admin/schedule";
    }

    @PostMapping("admin/schedule/save")
    public String saveDoctor(
            @RequestParam("docdate") Date date,
            @RequestParam("doctor_id") int doctor_id,
            @RequestParam("starttime") String starttimeStr,
            @RequestParam("endtime") String endtimeStr,
            @RequestParam("max") int max,
            Model model, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        java.sql.Time starttime = null;
        java.sql.Time endtime = null;
        Date now = new java.sql.Date(System.currentTimeMillis());
        try {
            java.util.Date starttimeUtil = format.parse(starttimeStr);
            java.util.Date endtimeUtil = format.parse(endtimeStr);
            starttime = new java.sql.Time(starttimeUtil.getTime());
            endtime = new java.sql.Time(endtimeUtil.getTime());
        } catch (Exception e) {
            // Handle the exception
        }

        slot slot = new slot();
        slot.setDoctor_id(doctor_id);
        if(date.after(now)) {
            slot.setDate(date);
        } else {
            redirectAttributes.addFlashAttribute("error", "Date must after now");
            return "redirect:/admin/schedule";
        }
        
        if(starttime.equals(endtime)) {
            redirectAttributes.addFlashAttribute("error", "Start time cannot be the same as end time");
            return "redirect:/admin/schedule";
        } else {
            slot.setStart_time(starttime);
            slot.setEnd_time(endtime);
        }
        
        slot.setMax_appointments_per_slot(max);

        redirectAttributes.addFlashAttribute("successmessage", "Doctor schedule added successfully!");

        ScheduleService.save(slot);

        return "redirect:/admin/schedule";
    }
}
