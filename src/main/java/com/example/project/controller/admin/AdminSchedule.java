package com.example.project.controller.admin;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
            @RequestParam("doctor_id") int doctor_id,
            @RequestParam("starttime") String starttimeStr,
            @RequestParam("endtime") String endtimeStr, @RequestParam("dayofweek") int dayofweek,
            Model model, ModelAndView modelAndView, RedirectAttributes redirectAttributes) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        java.sql.Time starttime;
        java.sql.Time endtime;

        java.util.Date starttimeUtil = format.parse(starttimeStr);
        java.util.Date endtimeUtil = format.parse(endtimeStr);
        starttime = new java.sql.Time(starttimeUtil.getTime());
        endtime = new java.sql.Time(endtimeUtil.getTime());

        List<slot> slotList = ScheduleService.checkSlotByDoctorIdAndDayOfWeekAndTime(dayofweek, doctor_id, endtime,
                starttime);
        if (!slotList.isEmpty()) {
            redirectAttributes.addFlashAttribute("errormessage", "Doctor schedule already exists!");
            return "redirect:/admin/schedule";
        } else {
            slot slot = new slot();
            slot.setDoctor_id(doctor_id);
            slot.setStart_time(starttime);
            slot.setEnd_time(endtime);
            slot.setDayof_week(dayofweek);
            slot.setMax_appointments_per_slot(5);
            ScheduleService.save(slot);
            redirectAttributes.addFlashAttribute("successmessage", "Doctor schedule added successfully!");
            return "redirect:/admin/schedule";
        }

    }
}
