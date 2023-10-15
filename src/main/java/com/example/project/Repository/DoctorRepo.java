package com.example.project.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.doctor;

@Repository
public interface DoctorRepo extends JpaRepository<doctor, Integer>{
    @Query( value ="select d.doctor_id,d.doctor_name,d.position,d.gender,d.phone,d.image,\n" + //
            "d.description,d.dob,d.status,d.create_at,d.create_by from doctor d , doctorservice ds, service s\n" + //
            "where d.doctor_id = ds.doctorID and s.service_id=ds.serviceID\n" + //
            "and s.service_id=?1",nativeQuery = true)
    Page<doctor> getDocByService(int id, PageRequest pageable);

    @Query(value = "select * from doctor d , doctorservice ds, service s\n" + //
            "where d.doctor_id = ds.doctorID and s.service_id=ds.serviceID\n" + //
            "and d.doctor_id=?1",nativeQuery=true)
    List<Object> getServiceByDoc(int id);

    @Query(value="select * from slot s join doctor d on s.doctor_id=d.doctor_id\n" + //
            "where d.doctor_id=?1 AND YEARWEEK(s.Date) = YEARWEEK(NOW())", nativeQuery = true)
    List<Object> getSlotByDoc(int id);

    @Query(value="select * from reservation r join feedbackreservation f \n" + //
            "on r.reservation_id=f.reservation_id\n" + //
            "where r.doctor_id=?1", nativeQuery = true)
    List<Object> getDocReview(int id);
}
