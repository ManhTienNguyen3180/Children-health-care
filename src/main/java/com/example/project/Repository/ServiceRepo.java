package com.example.project.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.project.entity.service;
@Repository
public interface ServiceRepo extends JpaRepository<service, Integer>{
    @Query("Select s from service s where s.service_id IN ?1")
    List<service> findByServiceId(List<Integer> serviceIds); 
    @Query( value ="select * from service s where s.service_name like %:key%",nativeQuery = true)
    Page<service> getServiceByKey(@Param("key")String name,PageRequest pageable);

    @Query( value ="select * from service s where s.category_id = ?1",nativeQuery = true)
    Page<service> getServiceByCategoryId(@Param("key")int id,PageRequest pageable);

    @Query( value ="select c.name from service s join category_service c on s.category_id=c.id where s.service_id=?1",nativeQuery = true)
    Object getCategoryName(int cid);

    @Query( value ="select * from doctor d , doctorservice ds, service s\n" + //
            "where d.doctor_id = ds.doctorID and s.service_id=ds.serviceID\n" + //
            "and s.service_id=?1",nativeQuery = true)
    List<Object> getDocByService(int id);

    @Query("select s from service s join doctorservice ds ON s.service_id = ds.serviceID where ds.doctorID = ?1")
    List<service> findServiceByDoctorID(int doctorID);
    @Query("Select s from service s where s.service_id NOT IN ?1")
    List<service> findNotByServiceId(List<Integer> serviceIds); 
}
