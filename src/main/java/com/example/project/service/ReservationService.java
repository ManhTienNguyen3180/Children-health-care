package com.example.project.service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.project.Repository.ReservationDetailRepo;
import com.example.project.Repository.ReservationRepo;
import com.example.project.dto.doctorserviceDTO;
import com.example.project.dto.slotDTO;
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


    public void save(reservation reservation) {
        repository.save(reservation);
    }

    public  List<doctorserviceDTO> getDoctorService(List<Integer> serviceid) {
        List<Object[]> listofdoctor = repository.findDoctorService(serviceid);
        List<doctorserviceDTO> listofdoctorDTO = new ArrayList<>();
        for(Object[] obj : listofdoctor) {
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
    public List<slotDTO> getDoctorSlot(int doctorid){
        List<Object[]> listofslot = repository.findDoctorSlot(doctorid);
        List<slotDTO> listofslotDTO = new ArrayList<>();
        for(Object[] obj : listofslot) {
            slotDTO slotDTO = new slotDTO();
            slotDTO.setId(Integer.parseInt(String.valueOf(obj[0])));
            slotDTO.setDoctor_id(Integer.parseInt(String.valueOf(obj[1])));
            slotDTO.setDate(String.valueOf(obj[2]));
            slotDTO.setMaxAppointmentsPerSlot(Integer.parseInt(String.valueOf(obj[3])));
            listofslotDTO.add(slotDTO);
        }
        return listofslotDTO;
    }

    public int getLastReservationId() {
        return repository.getLastReservationId();
    }
    public int countByReservationId(int doctorid, Date date) {
        return repository.countByReservationId(doctorid, date);
    }
    public void mergeReservationDetail(int reservation_id, int service_id, String service_name, int price, Date create_at, String create_by, int doctor_id, String doctor_name) {
        detailRepo.mergeReservationDetail(reservation_id,service_id,service_name,price,create_at,create_by,doctor_id,doctor_name);
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
    public List<reservation> listReservationByPatientId(int patientId) {
        return repository.findByPatient_id(patientId);
    }
}
