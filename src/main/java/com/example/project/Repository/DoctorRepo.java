package com.example.project.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.doctor;

@Repository
public interface DoctorRepo extends JpaRepository<doctor, Integer> {
    @Query("SELECT d FROM doctor d WHERE doctor_name=?1 and d.status=1")
    Optional<doctor> findDoctorByName(String doctor_name);

    @Query("SELECT p FROM doctor p WHERE CONCAT(p.doctor_name, p.position, p.phone) LIKE %?1% ")
    Page<doctor> search(String keyword, PageRequest pageable);

    @Query(value = "select * from doctor d where d.status=1", nativeQuery = true)
    Page<doctor> getAllPagi( PageRequest pageable);

    @Query(value = "select d.doctor_id,d.doctor_name,d.position,d.gender,d.phone,d.image,d.description,d.dob,d.status,d.create_at,d.create_by,d.doctorservice_id from doctor d , category_service c where d.doctorservice_id=c.id and c.id=?1 and d.status=1", nativeQuery = true)
    Page<doctor> getDocByService(int id, PageRequest pageable);

    @Query(value = "select * from doctor d , doctorservice ds, service s\n" + //
            "where d.doctor_id = ds.doctorID and s.service_id=ds.serviceID\n" + //
            "and d.doctor_id=?1 ", nativeQuery = true)
    List<Object> getServiceByDoc(int id);

    @Query(value = "select * from slot s join doctor d on s.doctor_id=d.doctor_id\n" + //
            "where d.doctor_id=?1 ", nativeQuery = true)
    List<Object> getSlotByDoc(int id);

    @Query(value = "select * from reservation r join feedbackreservation f \n" + //
            "on r.reservation_id=f.reservation_id\n" + //
            "where r.doctor_id=?1", nativeQuery = true)
    List<Object> getDocReview(int id);

    @Query( value ="select * from doctor d order by d.doctor_id desc limit 1",nativeQuery = true)
    doctor getLatestDoctor();

    @Query(value = "SELECT * FROM doctor  WHERE doctorservice_id = ?1", nativeQuery = true)
    Page<doctor> filterCategory(int id, PageRequest pageable);

    @Query(value = "SELECT * FROM doctor  WHERE status = ?1", nativeQuery = true)
    Page<doctor> filterStatus(int id, PageRequest pageable);

    @Query("SELECT p FROM doctor p WHERE p.position =?1 or p.phone = ?2 order by p.doctor_id asc limit 1")
    doctor findDoctor(String position, String phone);

    @Query("SELECT p FROM doctor p WHERE p.position like %?1% order by p.doctor_id asc limit 1")
    doctor findDoctorPosition(String position);
    
    //get doctor by doctorserviceid 
    @Query("SELECT d FROM doctor d where d.doctorserviceId=?1")
    public List<doctor> getDoctorByDoctorServiceID(int id); 
        
    @Query(value = "select c.name from doctor d , category_service c where d.doctor_id = c.id and d.doctor_id=?1", nativeQuery = true)
    String getCategoryNameByDoctorID(int id);
       
    @Query("SELECT d FROM doctor d where d.doctor_id = ?1")
    public doctor getDoctorByDocID(int doctor_id);
}
