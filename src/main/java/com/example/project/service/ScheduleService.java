package com.example.project.service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.SlotRepo;
import com.example.project.entity.slot;

@Service
public class ScheduleService {

    @Autowired
    private SlotRepo repo;

    public List<slot> fetchSlotList() {
        return (List<slot>) repo.findAll();
    }

    public void save(slot slot) {
        repo.save(slot);
    }
    
    @Autowired
    private SlotRepo slotRepository;
    
    
    // public List<slot> getSlotsForDoctor(int doctor_id) {
    //     return slotRepository.findByDoctor_id(doctor_id);
    // }

    public slot findSlotByDoctorID(int docID) {
        Optional<slot> useOptional = slotRepository.findSlotByDoctorId(docID);
        if (useOptional.isPresent()) {
            slot u = useOptional.get();
            return u;
        }
        return null;
    }

    
    public List<slot> getSlotsByDoctorId(int doctorId) {
        return repo.findByDoctorId(doctorId);
    }
    
    public List<slot> checkSlotByDoctorIdAndDayOfWeekAndTime(int dayOfWeek, int doctorId, Time endTime, Time startTime) {
        return repo.checkSlotByDoctorIdAndDayOfWeekAndTime(dayOfWeek, doctorId, endTime, startTime);
    }
    
}
