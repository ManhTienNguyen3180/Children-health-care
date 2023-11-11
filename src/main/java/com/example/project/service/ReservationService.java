package com.example.project.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.project.Repository.ReservationDetailRepo;
import com.example.project.Repository.ReservationRepo;
import com.example.project.Repository.SlotRepo;
import com.example.project.dto.doctorserviceDTO;
import com.example.project.dto.reservationDTO;
import com.example.project.dto.slotDTO;
import com.example.project.entity.doctor;
import com.example.project.entity.reservation;
import com.example.project.entity.reservationdetail;
import com.example.project.entity.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo repository;
    @Autowired
    ReservationDetailRepo detailRepo;
    @Autowired
    EmailService EmailService;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private SlotRepo slotRepo;

    public void save(reservation reservation) {
        repository.save(reservation);
    }
    
    


    public List<doctorserviceDTO> getDoctorService(List<Integer> serviceid) {
        List<Object[]> listofdoctor = repository.findDoctorService(serviceid);
        List<doctorserviceDTO> listofdoctorDTO = new ArrayList<>();
        for (Object[] obj : listofdoctor) {
            doctorserviceDTO doctorserviceDTO = new doctorserviceDTO();
            doctorserviceDTO.setDoctor_id(Integer.parseInt(String.valueOf(obj[0])));
            doctorserviceDTO.setDoctor_name(String.valueOf(obj[1]));
            doctorserviceDTO.setPosition(String.valueOf(obj[2]));
            doctorserviceDTO.setGender(Integer.parseInt(String.valueOf(obj[3])));
            doctorserviceDTO.setPhone(Integer.parseInt(String.valueOf(obj[4])));
            doctorserviceDTO.setImage(String.valueOf(obj[5]));
            doctorserviceDTO.setDescription(String.valueOf(obj[6]));
            doctorserviceDTO.setDob(String.valueOf(obj[7]));
            doctorserviceDTO.setStatus(Integer.parseInt(String.valueOf(obj[8])));
            doctorserviceDTO.setCreate_at(String.valueOf(obj[9]));
            doctorserviceDTO.setCreate_by(String.valueOf(obj[10]));
            listofdoctorDTO.add(doctorserviceDTO);
        }
        return listofdoctorDTO;
    }

    public List<slotDTO> getDoctorSlot(int doctorid) {
        List<Object[]> listofslot = repository.findDoctorSlot(doctorid);
        List<slotDTO> listofslotDTO = new ArrayList<>();
        for (Object[] obj : listofslot) {
            slotDTO slotDTO = new slotDTO();
            slotDTO.setDayof_week(Integer.parseInt(String.valueOf(obj[0])));
            slotDTO.setDoctor_id(Integer.parseInt(String.valueOf(obj[1])));
            listofslotDTO.add(slotDTO);
        }
        return listofslotDTO;
    }

    public List<slotDTO> getDate() {
        List<Object[]> listofslot = slotRepo.findDayOfWeek();
        List<slotDTO> listofslotDTO = new ArrayList<>();
        for (Object[] obj : listofslot) {
            slotDTO slotDTO = new slotDTO();
            slotDTO.setDayof_week(Integer.parseInt(String.valueOf(obj[0])));
            listofslotDTO.add(slotDTO);
        }
        return listofslotDTO;
    }

    public List<slotDTO> getTime(int dayofweek) {
        List<Object[]> listofslot = slotRepo.findTime(dayofweek);
        List<slotDTO> listofslotDTO = new ArrayList<>();
        for (Object[] obj : listofslot) {
            slotDTO slotDTO = new slotDTO();
            slotDTO.setStart_time(String.valueOf(obj[0]));
            slotDTO.setEnd_time(String.valueOf(obj[1]));
            listofslotDTO.add(slotDTO);
        }
        return listofslotDTO;
    }

    public List<slotDTO> getTimeSlotDTOs(int doctorid, int dayofweek) {
        List<Object[]> listofslot = slotRepo.findSlotByDoctorIdAndDayOfWeek(doctorid, dayofweek);
        List<slotDTO> listofslotDTO = new ArrayList<>();
        for (Object[] obj : listofslot) {
            slotDTO slotDTO = new slotDTO();
            slotDTO.setStart_time(String.valueOf(obj[0]));
            slotDTO.setEnd_time(String.valueOf(obj[1]));
            listofslotDTO.add(slotDTO);
        }
        return listofslotDTO;
    }

    public reservation findByDoctor_idAndDateAndTime(int doctor_id, Date date, String time) {
        return repository.findByDoctor_idAndDateAndTime(doctor_id, date, time);
    }
    public reservation findByDoctor_idAndDateAndTimeAndDay(int doctor_id, Date date, String time,int dayof_week) {
        return repository.findByDoctor_idAndDateAndTimeAndDay(doctor_id, date, time,dayof_week);
    }
    public int getLastReservationId() {
        return repository.getLastReservationId();
    }

    public int countByReservationId(int doctorid, Date date,String time) {
        return repository.countByReservationId(doctorid, date,time);
    }

    public void mergeReservationDetail(int reservation_id, int service_id, String service_name, int price,
            Date create_at, String create_by, int doctor_id, String doctor_name) {
        detailRepo.mergeReservationDetail(reservation_id, service_id, service_name, price, create_at, create_by,
                doctor_id, doctor_name);
    }

    public void sendEmail(String patient_email, String patient_name, String doctor_name, String date,
            List<service> services, int total_cost) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("taixexedo@gmail.com");
        helper.setTo(patient_email);
        helper.setSubject("Appointment Confirmation");

        Context context = new Context();

        // Đặt các biến để truyền dữ liệu vào mẫu
        context.setVariable("patientName", patient_name);
        context.setVariable("doctorName", doctor_name);
        context.setVariable("date", date);
        context.setVariable("services", services);
        context.setVariable("totalCost", total_cost);

        String emailContent = templateEngine.process("appointment-confirmation", context);
        helper.setText(emailContent, true);
        emailSender.send(message);
    }

    public reservation findReservation(int patientId, int reservation) {
        Optional<reservation> s = repository.findByPatient(patientId, reservation);
        if (s.isPresent()) {
            return s.get();
        }
        return null;
    }

    public List<reservation> listReservationByPatientId(int patientId) {
        return repository.findByPatient_id(patientId);
    }
    public Optional<reservation> findReByPid(int patientId) {
        return repository.findReByPid(patientId);
    }

    public List<reservation> findByPatientDate(int patient_id, String date) {
        return repository.findByPatientDate(patient_id, date);
    }

    public List<reservationDTO> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Object[]> page =  repository.getListReservation(pageable);
        List<Object[]> listB = page.getContent();
        List<reservationDTO> listBDTO = new ArrayList<>();
        for (Object[] b : listB) {
            reservationDTO reservationDTO = new reservationDTO();
            reservationDTO.setReservation_id(Integer.parseInt(String.valueOf(b[0])));
            reservationDTO.setPatient_name(String.valueOf(b[1]));
            reservationDTO.setPatient_email(String.valueOf(b[2]));
            
            reservationDTO.setGender(Integer.parseInt(String.valueOf(b[4])));
            reservationDTO.setDate(Date.valueOf(String.valueOf(b[5])));
            reservationDTO.setTime(String.valueOf(b[6]));
            reservationDTO.setDoctor_name(String.valueOf(b[7]));
            reservationDTO.setStatus(Integer.parseInt(String.valueOf(b[8])));
            listBDTO.add(reservationDTO);

        }
        return listBDTO;
    }
    public List<reservationDTO> findPaginatedByName(String name) {
        List<Object[]> listB = repository.getListReservationByName(name);
        List<reservationDTO> listBDTO = new ArrayList<>();
        for (Object[] b : listB) {
            reservationDTO reservationDTO = new reservationDTO();
            reservationDTO.setReservation_id(Integer.parseInt(String.valueOf(b[0])));
            reservationDTO.setPatient_name(String.valueOf(b[1]));
            reservationDTO.setPatient_email(String.valueOf(b[2]));
            
            reservationDTO.setGender(Integer.parseInt(String.valueOf(b[4])));
            reservationDTO.setDate(Date.valueOf(String.valueOf(b[5])));
            reservationDTO.setTime(String.valueOf(b[6]));
            reservationDTO.setDoctor_name(String.valueOf(b[7]));
            reservationDTO.setStatus(Integer.parseInt(String.valueOf(b[8])));
            listBDTO.add(reservationDTO);

        }
        return listBDTO;
    }

    public List<reservationDTO> findPaginatedFilter(int status,int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Object[]> page =  repository.getListReservationByStatus(status, pageable);
        List<Object[]> listB = page.getContent();
        List<reservationDTO> listBDTO = new ArrayList<>();
        for (Object[] b : listB) {
            reservationDTO reservationDTO = new reservationDTO();
            reservationDTO.setReservation_id(Integer.parseInt(String.valueOf(b[0])));
            reservationDTO.setPatient_name(String.valueOf(b[1]));
            reservationDTO.setPatient_email(String.valueOf(b[2]));
            
            reservationDTO.setGender(Integer.parseInt(String.valueOf(b[4])));
            reservationDTO.setDate(Date.valueOf(String.valueOf(b[5])));
            reservationDTO.setTime(String.valueOf(b[6]));
            reservationDTO.setDoctor_name(String.valueOf(b[7]));
            reservationDTO.setStatus(Integer.parseInt(String.valueOf(b[8])));
            listBDTO.add(reservationDTO);

        }
        return listBDTO;
    }

    public reservationDTO getReservationDTODetail(int reservation_id) {
        List<Object[]> listofreservation = detailRepo.getReservationDetail(reservation_id);
        reservationDTO reservationDTO = new reservationDTO();
        for (Object[] obj : listofreservation) {
            reservationDTO.setReservation_id(Integer.parseInt(String.valueOf(obj[0])));
            reservationDTO.setPatient_name(String.valueOf(obj[1]));
            reservationDTO.setPatient_email(String.valueOf(obj[2]));
           
            reservationDTO.setGender(Integer.parseInt(String.valueOf(obj[4])));
            reservationDTO.setDate(Date.valueOf(String.valueOf(obj[5])));
            reservationDTO.setTime(String.valueOf(obj[6]));
            reservationDTO.setDoctor_name(String.valueOf(obj[7]));
            reservationDTO.setStatus(Integer.parseInt(String.valueOf(obj[8])));
            reservationDTO.setPatient_phone(Integer.parseInt(String.valueOf(obj[9])));
            reservationDTO.setPatient_address(String.valueOf(obj[10]));
            reservationDTO.setNote(String.valueOf(obj[11]));
            reservationDTO.setPatient_id(Integer.parseInt(String.valueOf(obj[12])));
        }
        return reservationDTO;
    }

    public List<reservationdetail> getReservationdetail(int reservation_id) {

        return  detailRepo.getlistDetail(reservation_id);
    }


    public void DeleteService(int reid,int serviceid) {
        detailRepo.DeleteService(reid, serviceid);
    }

    public void editReservation(int id, int statusedit) {
        detailRepo.editReservation(id, statusedit);
    }
    public void editReservationDoc(int docid, String docname,int id) {
        detailRepo.editReservationDoc(docid, docname,id);
    }

    public reservation findReservationByID(int reservation_id) {
        return repository.findById(reservation_id).get();
    }

    public List<reservation> findReservationsByUserID(int user_id) {
        return repository.findReservationByUserId(user_id);
    }

    public List<reservationdetail> findAllReserDetail() {
        return detailRepo.findAll();
    }

    public List<reservationdetail> findReserDetailByReserID(int reservation_id) {
        return detailRepo.findByReservation_id(reservation_id);
    }

    public void deleteReservationDetail(int id) {
        detailRepo.deleteById(id);
    }

    public void deleteReservation(int id) {
        repository.deleteById(id);
    }

    public Page<reservation> findPaginated(int id, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.repository.findPageReservationByUserId(id, pageable);
    }
    public List<Integer> countReservationsByMonth(int year){
        return repository.getMonthlyReservationCounts(year);
    }
    public List<reservation> findAll(){
        return repository.findAll();
    }
}
