package com.example.project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;
import com.example.project.Repository.ReservationDetailRepo;
import com.example.project.dto.doctorserviceDTO;
import com.example.project.dto.slotDTO;
import com.example.project.entity.doctor;
import com.example.project.entity.doctorservice;
import com.example.project.entity.patient;
import com.example.project.entity.reservation;
import com.example.project.entity.reservationdetail;
import com.example.project.entity.service;
import com.example.project.entity.user;
import com.example.project.service.DoctorService;
import com.example.project.service.PatientService;
import com.example.project.service.ReservationService;
import com.example.project.service.ScheduleService;
import com.example.project.service.ServiceCategoryService;
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
    @Autowired
    ServiceCategoryService serviceCategoryService;
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("bookingappointment")
    public String getData(Model model, HttpSession session) {
        model.addAttribute("listSelected", session.getAttribute("selectedService"));
        model.addAttribute("listService", ServiceService.fechServicesList());
        model.addAttribute("listDoctor", DoctorService.fetchDoctorList());
        model.addAttribute("listCategoryService", serviceCategoryService.fetchServiceCategoryList());

        return "bookingappointment";
    }

    @GetMapping("/getServiceByCategoryServiceId/{categoryId}")
    @ResponseBody
    public List<service> chooseService(@PathVariable(value = "categoryId") int categoryId) {
        return ServiceService.findServiceByCategoryId(categoryId);
    }

    @GetMapping("/getDoctorByCategoryServiceId/{categoryId}")
    @ResponseBody
    public List<doctor> chooseDoctor(@PathVariable(value = "categoryId") int categoryId) {
        System.out.println(DoctorService.getDoctorByDoctorServiceID(categoryId).size());
        return DoctorService.getDoctorByDoctorServiceID(categoryId);
    }

    // @GetMapping("/getDoctorsByService/{serviceIds}")
    // @ResponseBody
    // public List<doctorserviceDTO> chooseDoc(@PathVariable List<Integer>
    // serviceIds) {

    // return ReservationService.getDoctorService(serviceIds);

    // }

    @GetMapping("getSlotsByDoctor/{doctorId}")
    @ResponseBody
    public List<slotDTO> chooseSlot(@PathVariable(value = "doctorId") int doctorId, HttpSession session) {

        return ReservationService.getDoctorSlot(doctorId);

    }

    @GetMapping("getDate")
    @ResponseBody
    public List<slotDTO> getDate(HttpSession session) {
        return ReservationService.getDate();
    }

    @GetMapping("getTime/{date}")
    @ResponseBody
    public List<slotDTO> getTime(@PathVariable(value = "date",required = false) String date, HttpSession session) {
        // get number of week day from date
        int dayOfWeek = getDayOfWeek(date);
        System.out.println("day= " + dayOfWeek);
        return ReservationService.getTime(dayOfWeek);
    }

    @GetMapping("getTimeByDoctorAndDate/{doctorId}/{date}")
    @ResponseBody
    public List<slotDTO> chooseTime(@PathVariable(value = "doctorId") int doctorId,
            @PathVariable(value = "date") String date, HttpSession session) {
        // get number of week day from date
        int dayOfWeek = getDayOfWeek(date);
        System.out.println("day= " + dayOfWeek);
        return ReservationService.getTimeSlotDTOs(doctorId, dayOfWeek);

    }

    @GetMapping("/reservationcontact")
    public String getContact(
            @RequestParam(value = "serviceId", required = false) List<Integer> service_id,
            @RequestParam(value = "doctorId", required = false) Integer doctorId, @RequestParam("date") String date,
            @RequestParam("time") String time, Model model,
            RedirectAttributes redirAttr,
            HttpSession session) {

        if (service_id != null) {
            Optional<doctor> optionalDoctor = DoctorService.findDoctorById(doctorId);
            doctor doctor = optionalDoctor.get();
            double totalPrice = 0.0;
            List<service> services = ServiceService.findListByServiceId(service_id);
            for (service service : services) {
                // Cập nhật tổng số tiền bằng cách thêm giá của mỗi dịch vụ
                totalPrice += service.getPrice();
            }
            model.addAttribute("doctor", doctor);
            model.addAttribute("service", ServiceService.findListByServiceId(service_id));
            model.addAttribute("date", date);
            model.addAttribute("time", time);
            model.addAttribute("totalPrice", totalPrice);
            session.setAttribute("selectedDate", date);
            session.setAttribute("selectedServices", ServiceService.findListByServiceId(service_id));
            session.setAttribute("selectedDoctors", doctor);
        }else{
            model.addAttribute("doctor", null);
            model.addAttribute("date", date);
            model.addAttribute("time", time);
            session.setAttribute("selectedDate", date);
            session.setAttribute("selectedServices", null);
            session.setAttribute("selectedDoctors", null);
            return "reservationcontact";
        }

        return "reservationcontact";

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

    public int getDayOfWeek(String dateStr) {
        try {
            // Định dạng của chuỗi ngày
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = dateFormat.parse(dateStr);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == Calendar.SUNDAY) {
                return 0;
            } else if (dayOfWeek == Calendar.MONDAY) {
                return 1;
            } else if (dayOfWeek == Calendar.TUESDAY) {
                return 2;
            } else if (dayOfWeek == Calendar.WEDNESDAY) {
                return 3;
            } else if (dayOfWeek == Calendar.THURSDAY) {
                return 4;
            } else if (dayOfWeek == Calendar.FRIDAY) {
                return 5;
            } else {
                return 6;
            }
        } catch (ParseException e) {
            // Xử lý lỗi khi định dạng ngày không hợp lệ
            e.printStackTrace();
            return -1; // Trả về một giá trị ngày không hợp lệ nếu có lỗi
        }
    }

    @GetMapping("/thankyou")
    public String thankyou() {
        return "thankyou";
    }
}
