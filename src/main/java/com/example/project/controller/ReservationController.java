package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.Repository.ReservationDetailRepo;
import com.example.project.dto.doctorserviceDTO;
import com.example.project.dto.slotDTO;
import com.example.project.entity.doctor;
import com.example.project.entity.patient;
import com.example.project.entity.reservation;
import com.example.project.entity.reservationdetail;
import com.example.project.entity.service;
import com.example.project.entity.user;
import com.example.project.service.DoctorService;
import com.example.project.service.PatientService;
import com.example.project.service.ReservationService;
import com.example.project.service.ServiceService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {
    @Autowired
    ServiceService ServiceService;

    @Autowired
    DoctorService DoctorService;

    @Autowired
    ReservationService ReservationService;
    @Autowired
    PatientService PatientService;
    @Autowired
    ReservationDetailRepo detailRepo;

    private CrudRepository<reservationdetail, Integer> ReservationDetailService;

    @GetMapping("bookingappointment")
    public String getData(Model model, HttpSession session) {
        model.addAttribute("listSelected", session.getAttribute("selectedService"));
        model.addAttribute("listService", ServiceService.fechServicesList());
        model.addAttribute("listDoctor", DoctorService.fetchDoctorList());

        return "bookingappointment";
    }

    @GetMapping("/getDoctorsByService/{serviceIds}")
    @ResponseBody
    public List<doctorserviceDTO> chooseDoc(@PathVariable List<Integer> serviceIds) {

        return ReservationService.getDoctorService(serviceIds);

    }

    @GetMapping("getSlotsByDoctor/{doctorId}")
    @ResponseBody
    public List<slotDTO> chooseSlot(@PathVariable(value = "doctorId") int doctorId) {
        return ReservationService.getDoctorSlot(doctorId);

    }

    @GetMapping("/reservationcontact")
    public String getContact(
            @RequestParam("serviceId") List<Integer> service_id,
            @RequestParam("doctorId") int doctorId, @RequestParam("date") String date, Model model,RedirectAttributes redirAttr,
            HttpSession session) {
        int count = ReservationService.countByReservationId(doctorId, java.sql.Date.valueOf(date));
        if (count >= 5) {
            redirAttr.addFlashAttribute("messageReser", "*This doctor is full on this day, please choose another day or another doctor");
            return "redirect:/bookingappointment";
        } else {
            Optional<doctor> optionalDoctor = DoctorService.findDoctorById(doctorId);
            doctor doctor = optionalDoctor.get();

            model.addAttribute("doctor", doctor);
            model.addAttribute("service", ServiceService.findListByServiceId(service_id));
            model.addAttribute("date", date);
            session.setAttribute("selectedDate", date);
            session.setAttribute("selectedServices", ServiceService.findListByServiceId(service_id));
            session.setAttribute("selectedDoctors", doctor);
            return "reservationcontact";
        }

    }

    @GetMapping("/reservationcontact/save")
    public String saveReservation(Model model, HttpSession session, @RequestParam("patient_name") String patient_name,
            @RequestParam("patient_email") String patient_email, @RequestParam("patient_phone") String patient_phone,
            @RequestParam("patient_address") String patient_address,
            @RequestParam("patient_note") String patient_note) throws MessagingException {
        reservation reservation = new reservation();
        // get data from session
        List<service> services = (List<service>) session.getAttribute("selectedServices");
        doctor doctor = (doctor) session.getAttribute("selectedDoctors");
        String date = (String) session.getAttribute("selectedDate");
        // create new patient and save into database
        patient patient = new patient();
        user user = (user) session.getAttribute("user");
        if (user != null) {
            patient.setPatient_name(patient_name);
            patient.setPatient_email(patient_email);
            patient.setPatient_phone(patient_phone);
            patient.setPatient_address(patient_address);
            patient.setDescription(patient_note);
            patient.setStatus(1);
            patient.setCreate_by("admin");
            patient.setCreate_at(new java.sql.Date(System.currentTimeMillis()));
            patient.setUser_id(user.getUser_id());

            PatientService.save(patient);
        } else {
            patient.setPatient_name(patient_name);
            patient.setPatient_email(patient_email);
            patient.setPatient_phone(patient_phone);
            patient.setPatient_address(patient_address);
            patient.setDescription(patient_note);
            patient.setStatus(1);
            patient.setCreate_by("admin");
            patient.setCreate_at(new java.sql.Date(System.currentTimeMillis()));

            PatientService.save(patient);
        }

        // get last patient id
        int patient_id = PatientService.getLastPatientId();
        // save reservation
        reservation.setPatient_id(patient_id);
        reservation.setPatient_name(patient_name);
        reservation.setDoctor_id(doctor.getDoctor_id());
        reservation.setDoctor_name(doctor.getDoctor_name());
        reservation.setDate(java.sql.Date.valueOf(date));
        reservation.setStatus(1);
        reservation.setCreate_at(new java.sql.Date(System.currentTimeMillis()));
        reservation.setCreate_by("admin");
        // total cost equal all service price in list service
        int total_cost = 0;
        for (service service : services) {
            total_cost += service.getPrice();
        }
        reservation.setTotal_cost(total_cost);
        // save reservation
        ReservationService.save(reservation);
        // get last reservation id
        int reservation_id = ReservationService.getLastReservationId();
        // save reservation detail

        for (service service : services) {
            // convert stirng date to date
            java.sql.Date date1 = java.sql.Date.valueOf(date);
            ReservationService.mergeReservationDetail(reservation_id, service.getService_id(),
                    service.getService_name(),
                    service.getPrice(), date1, "admin", doctor.getDoctor_id(), doctor.getDoctor_name());

        }
        // send email of reservation to patient email
        ReservationService.sendEmail(patient_email, patient_name, doctor.getDoctor_name(), date, services, total_cost);

        // remove session
        session.removeAttribute("selectedServices");
        session.removeAttribute("selectedDoctors");
        session.removeAttribute("selectedDate");

        return "redirect:/thankyou";
    }

    @GetMapping("/thankyou")
    public String thankyou() {
        return "thankyou";
    }
}
