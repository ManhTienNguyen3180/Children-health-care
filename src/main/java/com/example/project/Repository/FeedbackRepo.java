package com.example.project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.feedbackreservation;

@Repository
public interface FeedbackRepo extends JpaRepository<feedbackreservation, Integer>{ 
    @Query( value ="select f.id, r.reservation_id,r.patient_name,d.service_name,c.name,r.date,f.date,f.rating,r.doctor_name\n" + //
            "from reservation r , feedbackreservation f ,reservationdetail d, category_service c,service s\n" + //
            "where r.reservation_id = f.reservation_id \n" + //
            "and d.reservation_id=r.reservation_id\n" + //
            "and c.id=s.category_id\n" + //
            "and d.service_id=s.service_id",nativeQuery = true)
    List<Object[]> getAll();

    @Query( value ="select f.id, r.reservation_id,r.patient_name,d.service_name,c.name,r.date,f.date,f.rating,r.doctor_name\n" + //
            "from reservation r , feedbackreservation f ,reservationdetail d, category_service c,service s\n" + //
            "where r.reservation_id = f.reservation_id \n" + //
            "and d.reservation_id=r.reservation_id\n" + //
            "and c.id=s.category_id\n" + //
            "and d.service_id=s.service_id",nativeQuery = true)
    Page<Object[]> paginated(PageRequest pageable);
    @Query("SELECT u FROM feedbackreservation u where reservation_id=?1")
    Optional<feedbackreservation> findByReserId(int id);
}

