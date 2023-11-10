package com.example.project.controller.admin;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.Repository.ReservationRepo;
import com.example.project.dto.reservationDTO;
import com.example.project.entity.doctor;
import com.example.project.entity.reservation;
import com.example.project.entity.reservationdetail;
import com.example.project.entity.service;
import com.example.project.service.DoctorService;
import com.example.project.service.ReservationService;
import com.example.project.service.ServiceCategoryService;
import com.example.project.service.ServiceService;

import jakarta.websocket.server.PathParam;

/**
 * AdminAppointment
 */
@Controller
public class AdminAppointment {

  @Autowired
  ReservationService ReservationService;
  @Autowired
  ReservationRepo repository;

  @Autowired
  DoctorService doctorService;

  @Autowired
  ServiceService serviceService;
  @GetMapping("admin/appointment")
  public String page(Model model) {

    return list(1, model);
  }
  @GetMapping("admin/appointment/{name}")
  public String pagename(Model model,@PathVariable String name) {
    model.addAttribute("listReservation", ReservationService.findPaginatedByName(name));
    return "admin/appointment";
  }
  @GetMapping("/admin/appointment/detail")
  public String detailString() {
    return "admin/reservation-detail";
  }

  @GetMapping("/admin/appointment/edit/{id}")
  public String edit(@PathVariable int id, Model model) {
    reservationDTO reservationDTO = ReservationService.getReservationDTODetail(id);
    model.addAttribute("reservationDetail", ReservationService.getReservationdetail(id));
    model.addAttribute("reservationId", id);
    model.addAttribute("reservationStatus", reservationDTO.getStatus());
    model.addAttribute("datechoose", reservationDTO.getDate());
    model.addAttribute("timechoose", reservationDTO.getTime());
    doctor doctor = doctorService.findDoctorByDoctorName(reservationDTO.getDoctor_name());
    model.addAttribute("doctorname", reservationDTO.getDoctor_name());
    model.addAttribute("listService", serviceService.findServiceByCategoryId(doctor.getDoctorserviceId()));
    model.addAttribute("listDoctor", doctorService.getDoctorByDoctorServiceID(doctor.getDoctorserviceId()));
    return "admin/edit-appointment";
  }
  @GetMapping("/admin/appointment/delete/{reid}/{serviceid}")
  public String delete(@PathVariable int reid,@PathVariable int serviceid, Model model) {
    ReservationService.DeleteService(reid, serviceid);
    return "redirect:/admin/appointment/edit/" + reid;
  }

  @PostMapping("/admin/appointment/addservice")
  public String addservice(@RequestParam(value = "serviceId", required = false) List<Integer> service_id,
      @RequestParam(value = "reservationId", required = false) int reservation_id, Model model) {
    reservationDTO reservationDTO = ReservationService.getReservationDTODetail(reservation_id);
    doctor doctor = doctorService.findDoctorByDoctorName(reservationDTO.getDoctor_name());    
    List<service> services = serviceService.findListByServiceId(service_id);
    List<reservationdetail> reservationdetails = ReservationService.getReservationdetail(reservation_id);
    for (service service : services) {
      for (reservationdetail reservationdetail : reservationdetails) {
        if (reservationdetail.getService_id() == service.getService_id()) {
          model.addAttribute("error", "*Dịch vụ đã tồn tại");
          return "redirect:/admin/appointment/edit/" + reservation_id;
        }
      }
      java.sql.Date date1 = new java.sql.Date(reservationDTO.getDate().getTime());
      ReservationService.mergeReservationDetail(reservation_id, service.getService_id(), service.getService_name(), service.getPrice(), date1, "admin", doctor.getDoctor_id(), doctor.getDoctor_name());;
    }
    return "redirect:/admin/appointment/edit/" + reservation_id;
  }

  @RequestMapping(value = "/admin/appointment/edit/save", method = RequestMethod.POST)
  public String edit(@PathParam("id") int id, @PathParam("status") int status,
      @PathParam("statusedit") int statusedit, @PathParam("doctoredit") Integer doctoredit,
      @PathParam("datechoose") String datechoose, @PathParam("timechoose") String timechoose, Model model,
      RedirectAttributes redirectAttributes,@RequestParam(value = "serviceId", required = false) List<Integer> service_id) {

    if (status == 0 && ((statusedit != 1 || statusedit != 4))) {
      if (statusedit == 1 || statusedit == 4) {
        ReservationService.editReservation(id, statusedit);
        return "redirect:/admin/appointment";
      }
      redirectAttributes.addFlashAttribute("error", "*Lịch hẹn phải được chấp nhận hoặc từ chối trước khi chỉnh sửa");
      return "redirect:/admin/appointment/edit/" + id;
    } else if (status == 4) {
      redirectAttributes.addFlashAttribute("error", "*Lịch hẹn đã bị hủy không thể chỉnh sửa");
      return "redirect:/admin/appointment/edit/" + id;
    } else if (status == 2) {
      redirectAttributes.addFlashAttribute("error", "*Lịch hẹn đã được hoàn thành không thể chỉnh sửa");
      return "redirect:/admin/appointment/edit/" + id;
    } else if (doctoredit != null) {
      reservation reservation = ReservationService.findByDoctor_idAndDateAndTimeAndDay(doctoredit,
          java.sql.Date.valueOf(datechoose), timechoose, getDayOfWeek(datechoose));
      if (reservation != null) {
        redirectAttributes.addFlashAttribute("error", "*Bác sĩ đã có lịch hẹn vào thời gian này");
        return "redirect:/admin/appointment/edit/" + id;
      }
      ReservationService.editReservationDoc(doctoredit, doctorService.findById(doctoredit).getDoctor_name(), id);
      return "redirect:/admin/appointment/edit/" + id;
    }
    ReservationService.editReservation(id, statusedit);
    return "redirect:/admin/appointment";
  }

  @GetMapping("/admin/appointment/detail/{id}")
  public String detail(@PathVariable int id, Model model) {
    reservationDTO reservationDTO = ReservationService.getReservationDTODetail(id);
    model.addAttribute("reservationDTO", reservationDTO);
    model.addAttribute("reservationDetail", ReservationService.getReservationdetail(id));
    return "admin/reservation-detail";
  }

  @GetMapping("/admin/appointment/page/{pageNo}")
  public String list(@PathVariable int pageNo, Model model) {
    int pageSize = 7;
    PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
    Page<Object[]> page = (Page<Object[]>) repository.getListReservation(pageable);
    model.addAttribute("listReservation", ReservationService.findPaginated(pageNo, pageSize));
    model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", page.getTotalPages());
    return "admin/appointment";
  }
  
  public int getDayOfWeek(String dateStr) {
    try {
      // Định dạng của chuỗi ngày
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      java.util.Date date = dateFormat.parse(dateStr);

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

      if (dayOfWeek == Calendar.SUNDAY) {
        return 1;
      } else if (dayOfWeek == Calendar.MONDAY) {
        return 2;
      } else if (dayOfWeek == Calendar.TUESDAY) {
        return 3;
      } else if (dayOfWeek == Calendar.WEDNESDAY) {
        return 4;
      } else if (dayOfWeek == Calendar.THURSDAY) {
        return 5;
      } else if (dayOfWeek == Calendar.FRIDAY) {
        return 6;
      } else {
        return 7;
      }
    } catch (ParseException e) {
      // Xử lý lỗi khi định dạng ngày không hợp lệ
      e.printStackTrace();
      return -1; // Trả về một giá trị ngày không hợp lệ nếu có lỗi
    }
  }

  @GetMapping("/admin/appointment/filterStatus/{id}")
  public String filterStatus(Model model, @PathVariable("id") int id) {
    return filterStatusAndPaginated(model, id, 1);
  }

  @GetMapping("/admin/appointment/filterStatus/{id}/{pageNo}")
  public String filterStatusAndPaginated(Model model, @PathVariable("id") int id,
      @PathVariable(value = "pageNo") int pageNo) {
    int pageSize = 7;
    PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
    Page<Object[]> page = (Page<Object[]>) repository.getListReservationByStatus(id, pageable);

    model.addAttribute("status", id);

    model.addAttribute("listReservation", ReservationService.findPaginatedFilter(id, pageNo, pageSize));
    model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", page.getTotalPages());
    return "admin/appointment";
  }
}