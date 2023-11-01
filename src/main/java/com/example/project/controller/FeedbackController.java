package com.example.project.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.feedbackreservation;
import com.example.project.entity.reservation;
import com.example.project.service.FeedbackService;
import com.example.project.service.ReservationService;

@Controller
@RequestMapping("feedback")
public class FeedbackController {
  @Autowired
  private ReservationService reservationService;
  @Autowired
  private FeedbackService feedbackService;

  @GetMapping
  public String registerpage(Model model) {
    reservation re = reservationService.findReservation(69, 60);
    model.addAttribute("reDetail", re);
    return "form";
  }

  @PostMapping
  public String submit(Model model,
      @RequestParam("rate") String star,
      @RequestParam("hygiene") String hygiene,
      @RequestParam("waiting") String waiting,
      @RequestParam("nurseknowledge") String nurseknowledge,
      @RequestParam("nursepatience") String nursepatience,
      @RequestParam("doctorkind") String doctorkind,
      @RequestParam("doctorknowledge") String doctorknowledge,
      @RequestParam("schedule") String schedule,
      @RequestParam("comment") String comment,
      @RequestParam(value = "schedule[other]", required = false) String other) {

    feedbackreservation f = new feedbackreservation();
    f.setReservation_id(60);
    f.setRating(Integer.parseInt(star));
    f.setComment(comment);
    f.setDate(Date.valueOf(LocalDate.now()));
    f.setDoctor_Knowledge(doctorknowledge);
    f.setHygiene(hygiene);
    f.setNurse_Patience(nursepatience);
    f.setNurse_Knowledge(nurseknowledge);
    f.setWaiting_Time(waiting);
    if (schedule.equals("other")) {
      f.setSchedule_create(other);
    } else {
      f.setSchedule_create(schedule);
    }
    feedbackService.addNewFeedback(f);
   
    return registerpage(model);
  }
}
